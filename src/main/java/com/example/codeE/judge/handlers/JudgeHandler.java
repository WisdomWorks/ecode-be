package com.example.codeE.judge.handlers;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
@Setter
@ChannelHandler.Sharable
public class JudgeHandler extends ChannelInboundHandlerAdapter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Judge judge;

    private List<CodeExercise> problems;
    private List<RuntimeVersion> executors;
    private String working;
    private String name;
    private boolean isDisabled;
    private String judgeAddress;
    private int timeout;

    public JudgeHandler() {
        this.judge = new Judge();
        this.problems = new ArrayList<>();
        this.executors = new ArrayList<>();
        this.working = null;
        this.name = null;
        this.isDisabled = false;
        this.judgeAddress = null;
    }

    public void onConnect() {
        this.timeout = 15;
        //Output log
    }

    public static class Handlers {
        private static final Map<String, Function<ObjectNode, ObjectNode>> handlers;

        @Autowired
        private static RuntimeVersionService runtimeVersionService;

        private static final JudgeHandler judgeHandler = new JudgeHandler();

        static {
            handlers = new HashMap<>();
            handlers.put("handshake", Handlers::onHandshake);
            handlers.put("submission-request", Handlers::onSubmissionRequest);
            handlers.put("submission-processing", Handlers::onSubmissionProcessing);
            handlers.put("submission-acknowledged", Handlers::onSubmissionAcknowledged);
            handlers.put("submission-wrong-acknowledge", Handlers::onSubmissionWrongAcknowledge);
            handlers.put("supported-problems", Handlers::onSupportedProblems);
            handlers.put("grading-begin", Handlers::onGradingBegin);
            handlers.put("grading-end", Handlers::onGradingEnd);
            handlers.put("compile-error", Handlers::onCompileError);
            handlers.put("compile-message", Handlers::onCompileMessage);
            handlers.put("internal-error", Handlers::onInternalError);
            handlers.put("submission-terminated", Handlers::onSubmissionTerminated);
            handlers.put("batch-begin", Handlers::onBatchBegin);
            handlers.put("batch-end", Handlers::onBatchEnd);
            handlers.put("test-case-status", Handlers::onTestCaseStatus);
            handlers.put("malformed-packet", Handlers::onMalformedPacket);
            handlers.put("ping-response", Handlers::onPingResponse);
            // Add more handlers as needed
        }


        public static void onConnect() {
            judgeHandler.timeout = 15;
            //Output log
        }

        private static boolean authenticate(String id, String key) {
            if(!judgeHandler.judge.getAuthKey().equals(key)) {
                //log
                return false;
            }

            if (judgeHandler.judge.getIsBlocked()) {
                //log
                return false;
            }

            return true;
        }

        private static void connected() {
            judgeHandler.judge.setStartTime(LocalDateTime.now());
            judgeHandler.judge.setOnline(true);
            judgeHandler.judge.setProblemIds(
                    judgeHandler.problems.stream().map(CodeExercise::getCode).toList()
            );
            judgeHandler.judge.setRuntimeIds(
                    judgeHandler.executors.stream().map(RuntimeVersion::getLanguageId).toList()
            ); //????

            for(String langId: judgeHandler.judge.getRuntimeIds()) {
                for(int i=0; i<judgeHandler.executors.size(); i++) {
                    runtimeVersionService.saveRuntimeVersion(new RuntimeVersion(
                            langId,
                            judgeHandler.judge.getJudgeId(),
                            judgeHandler.executors.get(i).getName(),
                            judgeHandler.executors.get(i).getVersion(),
                            i));
                }
            }

            judgeHandler.judgeAddress = "localhost:9999";
            //log judge successfully authenticated
        }

        private static void disconnected() {
            judgeHandler.judge.setOnline(false);
        }

        // Handler methods
        public static ObjectNode onHandshake(ObjectNode packet) {
            if (!packet.has("id") || !packet.has("key")) {
                //log
                return null;
            }

            if(!authenticate(packet.get("id").asText(), packet.get("key").asText())) {
                //log
                return null;
            }

            judgeHandler.timeout = 60;

            JsonNode problemsNode = packet.get("problems");
            if (problemsNode.isArray()) {
                for (JsonNode problemNode : problemsNode) {
                    CodeExercise problem = new CodeExercise();
                    problem.setExerciseId(problemNode.get("code").asText());
                    problem.setExerciseName(problemNode.get("name").asText());
                    problem.setDescription(problemNode.get("description").asText());
                    problem.setTimeLimit(problemNode.get("time_limit").asDouble());
                    problem.setMemoryLimit(problemNode.get("memory_limit").asInt());
                    problem.setShortCircuit(problemNode.get("short_circuit").asBoolean());
                    JsonNode allowedLanguages = problemNode.get("allowed_languages");
                    problem.setAllowedLanguageIds(new ArrayList<>());
                    if (allowedLanguages.isArray()) {
                        for (JsonNode language : allowedLanguages) {
                            problem.getAllowedLanguageIds().add(language.get("key").asText());
                        }
                    }
                    judgeHandler.problems.add(problem);
                }
            }
            judgeHandler.name = packet.get("id").asText();

            ObjectNode response = JsonNodeFactory.instance.objectNode();
            response.put("name", "handshake-success");
            connected();
            return response;
        }

        public static ObjectNode onSubmissionRequest(ObjectNode packet) {
            String submissionId = packet.get("submission-id").asText();
            String problemId = packet.get("problem-id").asText();
            String language = packet.get("language").asText();
            String source = packet.get("source").asText();

            CodeSubmission data = getRelatedSubmissionData(submissionId);
            ObjectNode response = JsonNodeFactory.instance.objectNode();

            if (data != null) {
                response.put("name", "submission-request");
                response.put("submission-id", submissionId);
                response.put("problem-id", problemId);
                response.put("language", language);
                response.put("source", source);
                response.put("time-limit", data.getTime());
                response.put("memory-limit", data.getMemory());
//                packet.put("short-circuit", data.isShortCircuit());
            }

            JudgeHandler judgeHandler = ChannelHandlerContextHolder.getJudgeHandler();
            judgeHandler.setWorking(submissionId);
            return response;
        }

        private static CodeSubmission getRelatedSubmissionData(String id) {
            // Get related submission data
            return null;

        }

        public static ObjectNode onSubmissionProcessing(ObjectNode packet) {
            // Handle submission processing packet
            return null;

        }

        public static ObjectNode onSubmissionAcknowledged(ObjectNode packet) {
            return null;

            // Handle submission acknowledged packet
        }

        public static ObjectNode onSubmissionWrongAcknowledge(ObjectNode packet) {
            return null;

            // Handle submission wrong acknowledge packet
        }

        public static ObjectNode onSupportedProblems(ObjectNode packet) {
            return null;

            // Handle supported problems packet
        }

        public static ObjectNode onGradingBegin(ObjectNode packet) {
            return null;

            // Handle grading begin packet
        }

        public static ObjectNode onGradingEnd(ObjectNode packet) {
            return null;

            // Handle grading end packet
        }

        public static ObjectNode onCompileError(ObjectNode packet) {
            return null;

            // Handle compile error packet
        }

        public static ObjectNode onCompileMessage(ObjectNode packet) {
            return null;

            // Handle compile message packet
        }

        public static ObjectNode onInternalError(ObjectNode packet) {
            return null;

            // Handle internal error packet
        }

        public static ObjectNode onSubmissionTerminated(ObjectNode packet) {
            return null;

            // Handle submission terminated packet
        }

        public static ObjectNode onBatchBegin(ObjectNode packet) {
            return null;

            // Handle batch begin packet
        }

        public static ObjectNode onBatchEnd(ObjectNode packet) {
            return null;

            // Handle batch end packet
        }

        public static ObjectNode onTestCaseStatus(ObjectNode packet) {
            return null;

            // Handle test case status packet
        }

        public static ObjectNode onMalformedPacket(ObjectNode packet) {
            return null;

            // Handle malformed packet
        }

        public static ObjectNode onPingResponse(ObjectNode packet) {
            return null;

            // Handle ping response packet
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        System.out.println("Received from client: " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        try {
            String packetString = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
            JsonNode packet = mapper.readTree(packetString);
            Function<ObjectNode, ObjectNode> handler = JudgeHandler.Handlers.handlers.get(packet.get("name").asText());
            result = handler.apply((ObjectNode) packet);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = JsonNodeFactory.instance.objectNode();
            result.put("name", "bad-request");
        } finally {
            System.out.println("Sending to client: " + mapper.writeValueAsString(result));
            ByteBuf buf = Unpooled.wrappedBuffer(mapper.writeValueAsString(result).getBytes());
            final SpringBootHandler.WriteListener listener = new SpringBootHandler.WriteListener() {
                @Override
                public void messageRespond(boolean success) {
                    System.out.println(success ? "reply success" : "reply fail");
                }
            };

            ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (listener != null) {
                        listener.messageRespond(future.isSuccess());
                    }
                }
            });
        }
    }

    public interface WriteListener {
        void messageRespond(boolean success);
    }
}
