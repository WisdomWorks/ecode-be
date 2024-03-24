package com.example.codeE.judge.handlers;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;

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
    private String name;
    private boolean isDisabled;
    private String judgeAddress;
    private String working;
    private int timeout;

//    public JudgeHandler() {
//        this.judge = new Judge();
//        this.problems = new ArrayList<>();
//        this.executors = new ArrayList<>();
//        this.working = null;
//        this.name = null;
//        this.isDisabled = false;
//        this.judgeAddress = null;
//    }

    public void onConnect() {
        this.timeout = 15;
        //Output log
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String receivedMessage = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);

        // Print the received message to the console
        System.out.println("Received from client: " + receivedMessage);
        System.out.println(msg);

        // Write the received message back to the client
        ctx.write(msg);
        ctx.flush();

        // Log the outbound buffer's content before writing "a"

        // Write a new String message "a"
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes("YOLO".getBytes());
        ctx.write(buffer);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ChannelHandlerContextHolder.setJudgeHandler(this);
    }

    public static class Handlers {
        private static final Map<String, Function<ObjectNode, ObjectNode>> handlers;

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

        // Handler methods
        public static ObjectNode onHandshake(ObjectNode packet) {
            // Handle handshake packet
            return null;
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
}
