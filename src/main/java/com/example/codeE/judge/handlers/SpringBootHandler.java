package com.example.codeE.judge.handlers;
import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.judge.JudgeList;
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
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.function.Function;

@ChannelHandler.Sharable
public class SpringBootHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private final JudgeList judges;
    private HashMap<String, Function<ObjectNode, ObjectNode>> methodMap;

    @Autowired
    public SpringBootHandler(JudgeList judges) {
        this.judges = judges;

        methodMap = new HashMap<>();
        methodMap.put("submission-request", this::onSubmission);
        methodMap.put("terminate-submission'", this::onTermination);
        methodMap.put("disable-judge", this::onDisableJudge);
        methodMap.put("disconnect-judge", this::onDisconnectRequest);
    }

    public ObjectNode onSubmission(ObjectNode data) {
        String id = data.get("submission-id").asText();
        String problem = data.get("problem-id").asText();
        String language = data.get("language").asText();
        String source = data.get("source").asText();
        String judge_id = data.get("judge-id").asText();
        int priority = data.get("priority").asInt();

        System.out.println("Submission received: " + id + " " + problem + " " + language + " " + source + " " + judge_id + " " + priority);

        ObjectNode response = JsonNodeFactory.instance.objectNode();
        // Check priority here
        if (!this.judges.checkPriority(priority)) {
            response.put("name", "bad-request");
        }
        else {
            this.judges.judge(id, problem, language, source, judge_id, priority);
            response.put("name", "submission-received");
            response.put("submission-id", id);
        }
        return response;
    }

    public ObjectNode onTermination(ObjectNode data) {
        // return {'name': 'submission-received', 'judge-aborted': self.judges.abort(data['submission-id'])}
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("name", "submission-received");

        // Check if the judge was aborted
        // self.judges.abort(data['submission-id'])
        // For now, we'll just return false
        response.put("judge-aborted", false);
        return response;
    }

    public void onMalformed(JsonNode packet) {
        System.out.println("Malformed packet: " + packet);
    }

    public ObjectNode onDisableJudge(ObjectNode data) {
        String judge_id = data.get("judge-id").asText();
        boolean is_disabled = data.get("is-disabled").asBoolean();
        // self.judges.update_disable_judge(judge_id, is_disabled)
        return null;
    }

    public ObjectNode onDisconnectRequest(ObjectNode data) {
        String judge_id = data.get("judge-id").asText();
        boolean force = data.get("force").asBoolean();
        // self.judges.disconnect(judge_id, force=force)
        return null;
    }
    @SneakyThrows
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        System.out.println("Received from client: " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        try {
            String packetString = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
            JsonNode packet = mapper.readTree(packetString);
            Function<ObjectNode, ObjectNode> handler = this.methodMap.get(packet.get("name").asText());
            result = handler.apply((ObjectNode) packet);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = JsonNodeFactory.instance.objectNode();
            result.put("name", "bad-request");
        } finally {
            System.out.println("Sending to client: " + mapper.writeValueAsString(result));
            byte[] compressed = ZlibCompression.zlibify(mapper.writeValueAsString(result));
//            ByteBuf buf = Unpooled.wrappedBuffer(mapper.writeValueAsString(result).getBytes());
            ByteBuf buf = Unpooled.wrappedBuffer(compressed);
            final WriteListener listener = new WriteListener() {
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
