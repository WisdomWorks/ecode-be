package com.example.codeE.judge.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@ChannelHandler.Sharable
public class JudgeHandler extends ChannelInboundHandlerAdapter {

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
            // Handle submission request packet
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
}
