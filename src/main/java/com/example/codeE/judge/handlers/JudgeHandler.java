package com.example.codeE.judge.handlers;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.model.exercise.common.LanguageLimit;
import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.model.exercise.common.SubmissionData;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.LanguageLimitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
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

        @Autowired
        private static CodeSubmissionRepository codeSubmissionRepository;

        @Autowired
        private static CodeSubmissionService codeSubmissionService;

        @Autowired
        private static CodeExerciseService codeExerciseService;

        @Autowired
        private static LanguageLimitService languageLimitService;

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

            //log
            return !judgeHandler.judge.getIsBlocked();
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
                    problem.setMemoryLimit(problemNode.get("memory_limit").asDouble());
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
            System.out.println(response);
            return response;
        }

        public static ObjectNode onSubmissionRequest(ObjectNode packet) {
            String submissionId = packet.get("submission-id").asText();
            String problemId = packet.get("problem-id").asText();
            String language = packet.get("language").asText();
            String source = packet.get("source").asText();

            SubmissionData data = getRelatedSubmissionData(submissionId);
            ObjectNode response = JsonNodeFactory.instance.objectNode();

            if (data != null) {
                response.put("name", "submission-request");
                response.put("submission-id", submissionId);
                response.put("problem-id", problemId);
                response.put("language", language);
                response.put("source", source);
                response.put("time-limit", data.getTime());
                response.put("memory-limit", data.getMemory());
                response.put("short-circuit", data.getShortCircuit());
//                response.put("pretests-only", data.isPretests_only());
            }

            judgeHandler.working = submissionId;
            return response;
        }

        private static boolean isWorking() {
            return judgeHandler.working != null;
        }

        private static SubmissionData getRelatedSubmissionData(String id) {
            try {
                CodeSubmission submission = codeSubmissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Submission not found"));

                CodeExercise problem = codeExerciseService.getProblemById(submission.getExerciseId());
                String problemId = submission.getExerciseId();
                Double timeLimit = problem.getTimeLimit();
                Double memoryLimit = problem.getMemoryLimit();
                Boolean shortCircuit = problem.getShortCircuit();
                String languageId = submission.getLanguageId();
                Boolean isPretested = submission.isPretested();

                try {
                    LanguageLimit languageLimit = languageLimitService.findByProblemIdAndLanguageId(problemId, languageId);

                    Double timeLimitLanguageLimit = languageLimit.getTimeLimit();
                    Integer memoryLimitLanguageLimit = languageLimit.getMemoryLimit();
                } catch (Exception e) {
                    LoggerHelper.logError("Submission data not found: " + id);
                    return null;
                } //???

                return new SubmissionData(
                        timeLimit,
                        memoryLimit,
                        shortCircuit,
                        isPretested
                );
            } catch (Exception e) {
                LoggerHelper.logError("Submission data not found: " + id);
                return null;
            }
        }

        public static ObjectNode onSubmissionProcessing(ObjectNode packet) {
            String id = packet.get("submission-id").asText();
            String judgeId = packet.get("judge-id").asText();


            Optional<CodeSubmission> submissionOptional = codeSubmissionRepository.findById(id);

            if (submissionOptional.isPresent()) {
                CodeSubmission submission = submissionOptional.get();
                submission.setStatus("P");
                submission.setJudgedOn(judgeId);
                codeSubmissionRepository.save(submission);

                LoggerHelper.logInfo("Submission processing: " + submission);
            } else {
                LoggerHelper.logWarning("Unknown submission: " + id);
            }

            return packet;
        }

        public static ObjectNode onSubmissionAcknowledged(ObjectNode packet) {
            String submissionId = packet.has("submission-id") ? packet.get("submission-id").asText() : null;
            String working = judgeHandler.working; // judgeId
            if (submissionId == null || !submissionId.equals(working)) {
                LoggerHelper.logError("Wrong acknowledgement: "+ judgeHandler.getName() + ": "+ submissionId+ ", expected: " + working);
                onSubmissionWrongAcknowledge(JsonNodeFactory.instance.objectNode()
                        .put("expected", working)
                        .put("got", submissionId));
            } else {
                LoggerHelper.logInfo("Submission acknowledged: " + working);
                onSubmissionProcessing(packet);
            }

            return packet;
        }

        public static ObjectNode onSubmissionWrongAcknowledge(ObjectNode packet) {
            String expected = packet.get("expected").asText();
            String got = packet.get("got").asText();

            codeSubmissionService.updateStatusAndResult(expected, "IE", "IE");
            codeSubmissionService.updateStatusAndResultBySubmissionIdAndStatus(got, "QU", "IE", "IE");
            return packet;
        }

        public static ObjectNode onSupportedProblems(ObjectNode packet) {
            return null;

            // Handle supported problems packet
        }

        public static ObjectNode onGradingBegin(ObjectNode packet) {
            // log Grading has begun
            CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(packet.get("submission-id").asText());
            codeSubmission.setStatus("G");
            codeSubmission.setPretested(packet.get("pretested").asBoolean());
            codeSubmission.setCurrentTestcase(1);

            if (codeSubmissionService.updateCodeSubmission(codeSubmission) != null){
                //log
            } else {
                //log
            }
            return null;
        }

//        def on_grading_begin(self, packet):
//                logger.info("%s: Grading has begun on: %s", self.name, packet["submission-id"])
//                # self.batch_id = None
//
//        if Submission.objects.filter(id=packet["submission-id"]).update(
//                status="G",
//                is_pretested=packet["pretested"],
//                current_testcase=1,
//                batch=False,
//                judged_date=timezone.now(),
//        ):
//                SubmissionTestCase.objects.filter(
//        submission_id=packet["submission-id"]
//                ).delete()
//            # event.post(
//                    #     "sub_%s" % Submission.get_id_secret(packet["submission-id"]),
//                #     {"type": "grading-begin"},
//                # )
//                # self._post_update_submission(packet["submission-id"], "grading-begin")
//                json_log.info(self._make_json_log(packet, action="grading-begin"))
//                else:
//                logger.warning("Unknown submission: %s", packet["submission-id"])
//                json_log.error(
//                self._make_json_log(
//        packet, action="grading-begin", info="unknown submission"
//                )
//                )

        public static ObjectNode onGradingEnd(ObjectNode packet) {
            return null;

            // Handle grading end packet
        }

        public static ObjectNode onCompileError(ObjectNode packet) {
            return null;

            // Handle compile error packet
        }

        public static ObjectNode onCompileMessage(ObjectNode packet) {
            //log
            CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(packet.get("submission-id").asText());
            codeSubmission.setError(packet.get("log").asText());
            if (codeSubmissionService.updateCodeSubmission(codeSubmission) != null){
                //log
            } else {
                //log
            }
            return null;
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
