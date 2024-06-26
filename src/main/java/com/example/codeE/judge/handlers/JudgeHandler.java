package com.example.codeE.judge.handlers;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.judge.JudgeList;
import com.example.codeE.judge.configurations.JudgeHandlerVariables;
import com.example.codeE.judge.configurations.JudgeHandlerVariables.*;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.model.exercise.common.LanguageLimit;
import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.LanguageLimitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.zip.DataFormatException;

import static com.example.codeE.judge.configurations.JudgeHandlerVariables.*;


@Getter
@Setter
@ChannelHandler.Sharable
public class JudgeHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext ctx;
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Judge judge;
    private JudgeList judgeList = new JudgeList(this);
    
    @Autowired
    private RuntimeVersionService runtimeVersionService;
    private Map<String, Function<ObjectNode, ObjectNode>> handlers;

    @Autowired
    private  CodeSubmissionService codeSubmissionService;

    @Autowired
    private  CodeExerciseService codeExerciseService;

    @Autowired
    private  LanguageLimitService languageLimitService;

    @Autowired
    private  SubmissionTestCaseService submissionTestCaseService;

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Client disconnected");
    }

    public JudgeHandler(){
        this.judge = new Judge();
       


        handlers = new HashMap<>();
        handlers.put("handshake", this::onHandshake);
        handlers.put("submission-processing", this::onSubmissionProcessing);
        handlers.put("submission-acknowledged", this::onSubmissionAcknowledged);
        handlers.put("supported-problems", this::onSupportedProblems);
        handlers.put("grading-begin", this::onGradingBegin);
        handlers.put("grading-end", this::onGradingEnd);
        handlers.put("compile-error", this::onCompileError);
        handlers.put("compile-message", this::onCompileMessage);
        handlers.put("internal-error", this::onInternalError);
        handlers.put("submission-terminated", this::onSubmissionTerminated);
        handlers.put("test-case-status", this::onTestCaseStatus);
        handlers.put("malformed-packet", this::onMalformedPacket);
        handlers.put("ping-response", this::onPingResponse);

    }


    public void onConnect() {
//        this.timeout = 15;
        //Output log
    }

    private boolean authenticate(String id, String key) {
        if(!this.judge.getAuthKey().equals(key)) {
            //log
            return false;
        }

        //log
        return !this.judge.getIsBlocked();
    }

    private void connected() {
        this.judge.setStartTime(LocalDateTime.now());
        this.judge.setOnline(true);
        this.judge.setProblemIds(new ArrayList<>(problems.keySet()));
        this.judge.setRuntimeIds(
                executors.keySet().stream().toList()
        );

        this.runtimeVersionService.deleteAllRuntimeVersion();

        for (Map.Entry<String, List<List<Object>>> entry : executors.entrySet()) {
            String languageId = entry.getKey(); // Lấy languageId từ key
            List<List<Object>> values = entry.getValue(); // Lấy danh sách các giá trị từ value

            // Lặp qua danh sách các giá trị
            for (List<Object> value : values) {
                String name = (String) value.get(0); // Lấy name từ phần tử đầu tiên
                List<Integer> versionList = (List<Integer>) value.get(1); // Lấy danh sách version

                // Ghép version thành một chuỗi "x.y.z"
                StringBuilder versionBuilder = new StringBuilder();
                for (int i = 0; i < versionList.size(); i++) {
                    versionBuilder.append(versionList.get(i));
                    if (i < versionList.size() - 1) {
                        versionBuilder.append(".");
                    }
                }
                String version = versionBuilder.toString();

                // Tạo đối tượng RuntimeVersion và thêm vào ArrayList
                this.runtimeVersionService.saveRuntimeVersion(new RuntimeVersion(
                        languageId,
                        this.judge.getJudgeId(),
                        name,
                        version,
                        0
                ));
            }
        }

        JudgeHandlerVariables.judgeAddress = "localhost:9999";
        //log judge successfully authenticated
    }

    private void disconnected() {
        this.judge.setOnline(false);
    }

    // Handler methods
    public ObjectNode onHandshake(ObjectNode packet) {
        if (!packet.has("id") || !packet.has("key")) {
            //log
            return null;
        }

        if(!authenticate(packet.get("id").asText(), packet.get("key").asText())) {
            //log
            return null;
        }

//        this.timeout = 60;

        JsonNode problemsNode = packet.get("problems");
        if (problemsNode.isArray()) {
            for (JsonNode problem : problemsNode) {
                // Extract the problem name and mtime from the array element
                String problemName = problem.get(0).asText();
                double mtime = problem.get(1).asDouble();

                // Add the problem to the Map
                problems.put(problemName, mtime);
            }
        }

        JsonNode executorsNode = packet.get("executors");
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = executorsNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String key = field.getKey();
            List<List<Object>> value = new ArrayList<>();
            JsonNode innerArrayNode = field.getValue();
            if (innerArrayNode.isArray()) {
                for (JsonNode arrayNode : innerArrayNode) {
                    List<Object> innerList = new ArrayList<>();
                    for (JsonNode innerNode : arrayNode) {
                        if (innerNode.isTextual()) {
                            innerList.add(innerNode.textValue());
                        } else if (innerNode.isArray()) {
                            List<Object> innerArray = new ArrayList<>();
                            for (JsonNode arrayElement : innerNode) {
                                if (arrayElement.isNumber()) {
                                    innerArray.add(arrayElement.numberValue());
                                }
                            }
                            innerList.add(innerArray);
                        }
                    }
                    value.add(innerList);
                }
            }
            executors.put(key, value);
        }

        JudgeHandlerVariables.name = packet.get("id").asText();

        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("name", "handshake-success");
        pingThread = new Thread(this::pingThread, "PingThread");
        pingThread.start();

        connected();
        return response;
    }

    public void submit(String submissionId, String problemId, String language, String source) {
        SubmissionData data = getRelatedSubmissionData(submissionId);
        ObjectNode response = JsonNodeFactory.instance.objectNode();

        if (data != null) {

            response.put("name", "submission-request");
            response.put("submission-id", Integer.parseInt(submissionId));
            response.put("problem-id", problemId);
            response.put("language", language);
            response.put("source", source);
            response.put("time-limit", data.time);
            response.put("memory-limit", data.memory);
            response.put("short-circuit", false);

            ObjectNode metaNode = JsonNodeFactory.instance.objectNode();
            metaNode.put("pretests-only", data.pretests_only);
            metaNode.put("in-contest", false);
            metaNode.put("attempt-no", 1);
            metaNode.put("user","id");

            response.set("meta", metaNode);
        }

        JudgeHandlerVariables.working = submissionId;
        send(response);
    }

    public boolean isWorking() {
        return JudgeHandlerVariables.working != null;
    }

    private  SubmissionData getRelatedSubmissionData(String id) {
        try {
            CodeSubmission submission = codeSubmissionService.getCodeSubmissionById(id);
            if (submission == null) {
                LoggerHelper.logError("Submission vanished: " + id);
                return null;
            }

            CodeExercise problem = codeExerciseService.getProblemById(submission.getExerciseId());
            String problemId = submission.getExerciseId();
            Double timeLimit = problem.getTimeLimit();
            Integer memoryLimit = problem.getMemoryLimit();
            Boolean shortCircuit = false;
            String languageId = submission.getLanguageId();
            Boolean isPretested = submission.isPretested();

            try {
                LanguageLimit languageLimit = languageLimitService.findByProblemIdAndLanguageId(problemId, languageId);

                timeLimit = languageLimit.getTimeLimit();
                memoryLimit = languageLimit.getMemoryLimit();
            } catch (Exception ignored) {

            }

            return new SubmissionData(
                    timeLimit,
                    memoryLimit,
                    shortCircuit,
                    isPretested
            );
        } catch (Exception e) {
            e.printStackTrace();
            LoggerHelper.logError("Something wrong with submission " + id);
            return null;
        }
    }

    public ObjectNode onSubmissionProcessing(ObjectNode packet) {
        String id = packet.get("submission-id").asText();

        CodeSubmission submission = codeSubmissionService.getCodeSubmissionById(id);

        if (submission != null) {
            submission.setStatus("P");
            submission.setJudgedOn(this.judge.getJudgeId());
            codeSubmissionService.updateCodeSubmission(submission);

            LoggerHelper.logInfo("Submission processing: " + submission);
        } else {
            LoggerHelper.logWarning("Unknown submission: " + id);
        }

        return packet;
    }

    public  ObjectNode onSubmissionAcknowledged(ObjectNode packet) {
        String submissionId = packet.has("submission-id") ? packet.get("submission-id").asText() : null;
        String working = JudgeHandlerVariables.working; // judgeId
        if (submissionId == null || !submissionId.equals(working)) {
            LoggerHelper.logError("Wrong acknowledgement: "+ JudgeHandlerVariables.name + ": "+ submissionId+ ", expected: " + working);
            onSubmissionWrongAcknowledge(packet, working, submissionId);
        } else {
            LoggerHelper.logInfo("Submission acknowledged: " + working);
            onSubmissionProcessing(packet);
        }

        return null;
    }

    public  void onSubmissionWrongAcknowledge(ObjectNode packet, String expected, String got) {
        codeSubmissionService.updateStatusAndResult(expected, "IE", "IE");
        codeSubmissionService.updateStatusAndResultBySubmissionIdAndStatus(got, "QU", "IE", "IE");
    }

    public  ObjectNode onSupportedProblems(ObjectNode packet) {
//        LoggerHelper.logInfo(this.name + ": Updated problem list" );
//        JsonNode problemsNode = packet.get("problems");
//
//        if (problemsNode.isArray()) {
//            for (JsonNode problemNode : problemsNode) {
//                CodeExercise problem = new CodeExercise();
//                problem.setExerciseId(problemNode.get("code").asText());
//                problem.setExerciseName(problemNode.get("name").asText());
//                problem.setDescription(problemNode.get("description").asText());
//                problem.setTimeLimit(problemNode.get("time_limit").asDouble());
//                problem.setMemoryLimit(problemNode.get("memory_limit").asInt());
//                problem.setShortCircuit(problemNode.get("short_circuit").asBoolean());
//                JsonNode allowedLanguages = problemNode.get("allowed_languages");
//                problem.setAllowedLanguageIds(new ArrayList<>());
//                if (allowedLanguages.isArray()) {
//                    for (JsonNode language : allowedLanguages) {
//                        problem.getAllowedLanguageIds().add(language.get("key").asText());
//                    }
//                }
//                this.problems.add(problem);
//            }
//        }
        return null;
    }

    public  ObjectNode onGradingBegin(ObjectNode packet) {
        // log Grading has begun
        String submissionId = packet.get("submission-id").asText();
        CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(submissionId);
        codeSubmission.setStatus("G");
        codeSubmission.setPretested(packet.get("pretested").asBoolean());
        codeSubmission.setCurrentTestcase(1);

        if (codeSubmissionService.updateCodeSubmission(codeSubmission) != null){
            submissionTestCaseService.deleteAllTcBySubmissionId(submissionId);
            //log
        } else {
            //log
        }
        return null;
    }

    public ObjectNode onGradingEnd(ObjectNode packet) {
        this.freeSelf(packet);
        CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(packet.get("submission-id").asText());
        if (codeSubmission == null) {
            LoggerHelper.logError("Unknown submission: " + packet.get("submission-id").asText());
            return null;
        }

        Double time = 0.0;
        Integer memory = 0;
        Double points = 0.0;
        Double total = 0.0;
        Integer status = 0;
        List<String> statusCodes = List.of("SC", "AC", "WA", "MLE", "TLE", "IR", "RTE", "OLE");

        List<SubmissionTestCase> testCases = submissionTestCaseService.findBySubmissionId(codeSubmission.getSubmissionId());
        for (SubmissionTestCase testCase : testCases) {
            time += testCase.getTime();
            memory = Math.max(memory, testCase.getMemory().intValue());
            points += testCase.getPoints();
            total += testCase.getTotal();
            status = Math.max(status, statusCodes.indexOf(testCase.getStatus()));
        }

        points = (double) Math.round(points * 10) / 10;
        total = (double) Math.round(total * 10) / 10;

        codeSubmission.setCasePoints(points);
        codeSubmission.setScore(points.floatValue());
        codeSubmission.setCaseTotal(total);

        String problemId = codeSubmission.getExerciseId();
        CodeExercise problem = codeExerciseService.getProblemById(problemId);
        Double problemPoints = problem.getPoints();
        Double subPoints = 0.0;
        if (total > 0) {
            subPoints = (double) Math.round((points / total) * problemPoints * 1000) / 1000;
        }
        if (!problem.isPartial() && !subPoints.equals(problemPoints)) {
            subPoints = 0.0;
        }

        codeSubmission.setStatus("D");
        codeSubmission.setTime(time);
        codeSubmission.setMemory(memory);
        codeSubmission.setCasePoints(points);
        codeSubmission.setScore(points.floatValue());
        codeSubmission.setResult(statusCodes.get(status));

        codeSubmissionService.updateCodeSubmission(codeSubmission);
        if (problem.isUsingAiGrading() && !codeSubmission.isPretested()){
            codeSubmissionService.overriedByAiGrader(codeSubmission.getSubmissionId(), codeSubmission.getExerciseId());
        }
        return null;
    }

    public  ObjectNode onCompileError(ObjectNode packet) {
        String submissionId = packet.get("submission-id").asText();
        String log = packet.get("log").asText();

        LoggerHelper.logInfo(JudgeHandlerVariables.name + ": Submission failed to compile: " + submissionId);
        CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(submissionId);
        if (codeSubmission != null) {
            codeSubmission.setStatus("CE");
            codeSubmission.setResult("CE");
            codeSubmission.setError(log);
            codeSubmissionService.updateCodeSubmission(codeSubmission);
        } else {
            LoggerHelper.logError("Unknown submission: " + submissionId);
        }

        this.freeSelf(packet);
        return null;
    }

    public  ObjectNode onCompileMessage(ObjectNode packet) {
        //log
        String submissionId = packet.get("submission-id").asText();
        CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(submissionId);
        if ( codeSubmission != null){
            codeSubmission.setError(packet.get("log").asText());
            codeSubmissionService.updateCodeSubmission(codeSubmission);
            //log
        } else {
            LoggerHelper.logError("Unknown submission: " + submissionId);
        }
        return null;
    }

    public  ObjectNode onInternalError(ObjectNode packet) {
        String submissionId = packet.get("submission-id").asText();
        String message = packet.get("message").asText();

        try {
            throw new Exception("\n\n" + message);
        } catch (Exception e) {
            LoggerHelper.logError("Judge " + JudgeHandlerVariables.name + " failed while handling submission " + submissionId, e);
        }
        CodeSubmission codeSubmission = codeSubmissionService.getCodeSubmissionById(submissionId);
        if (codeSubmission != null) {
            codeSubmission.setStatus("IE");
            codeSubmission.setResult("IE");
            codeSubmission.setError(message);
            codeSubmissionService.updateCodeSubmission(codeSubmission);
        } else {
            LoggerHelper.logError("Unknown submission: " + submissionId);
        }
        this.freeSelf(packet);
        return null;
    }

    public  ObjectNode onSubmissionTerminated(ObjectNode packet) {
        String submissionId = packet.get("submission-id").asText();
        LoggerHelper.logInfo(JudgeHandlerVariables.name + ": Submission aborted: " + submissionId);
        if(codeSubmissionService.getCodeSubmissionById(submissionId) != null) {
            codeSubmissionService.updateStatusAndResult(submissionId, "AB", "AB");
        } else {
            LoggerHelper.logError("Unknown submission: " + submissionId);
        }
        this.freeSelf(packet);
        return null;
    }

    public  ObjectNode onTestCaseStatus(ObjectNode packet) {
        //log
        String submissionId = packet.get("submission-id").asText();
        List<SubmissionTestCase> updates = new ArrayList<>();
        JsonNode cases = packet.get("cases");
        if (cases.isArray()) {
            for (JsonNode testCase : cases) {
                SubmissionTestCase submissionTestCase = new SubmissionTestCase();

                submissionTestCase.setSubmissionId(submissionId);
                submissionTestCase.setTestCaseId(testCase.get("position").asInt());
                int status = testCase.get("status").asInt();
                // TODO: Fix this
                if ((status & 4) != 0) {
                    submissionTestCase.setStatus("TLE");
                } else if ((status & 8) != 0) {
                    submissionTestCase.setStatus("MLE");
                } else if ((status & 64) != 0) {
                    submissionTestCase.setStatus("OLE");
                } else if ((status & 2) != 0) {
                    submissionTestCase.setStatus("RTE");
                } else if ((status & 16) != 0) {
                    submissionTestCase.setStatus("IR");
                } else if ((status & 1) != 0) {
                    submissionTestCase.setStatus("WA");
                } else if ((status & 32) != 0) {
                    submissionTestCase.setStatus("SC");
                } else {
                    submissionTestCase.setStatus("AC");
                }
                submissionTestCase.setTime(testCase.get("time").asDouble());
                submissionTestCase.setMemory(testCase.get("memory").asDouble());
                submissionTestCase.setPoints(testCase.get("points").asDouble());
                submissionTestCase.setTotal(testCase.get("total-points").asDouble());
                submissionTestCase.setFeedback(testCase.get("feedback").asText());
                submissionTestCase.setExtendedFeedback(testCase.get("extended-feedback").asText());
                submissionTestCase.setOutput(testCase.get("output").asText());

                updates.add(submissionTestCase);
            }
        }
        int maxPosition = submissionTestCaseService.getMaxPosition(updates);
        CodeSubmission submission = codeSubmissionService.getCodeSubmissionById(submissionId);
        submission.setCurrentTestcase(maxPosition);
        if (codeSubmissionService.updateCodeSubmission(submission) == null){
            //log
        }

        submissionTestCaseService.saveAll(updates);

        return null;
    }

    public  ObjectNode onMalformedPacket(ObjectNode packet) {
        return null;

        // Handle malformed packet
    }

    public void ping() {
        ObjectNode packet = JsonNodeFactory.instance.objectNode();
        packet.put("name", "ping");
        packet.put("when", System.currentTimeMillis());

        send(packet);
    }

    private void pingThread() {
        try {
            while (true) {
                ping();
                if (stopPing.await(10, TimeUnit.SECONDS)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerHelper.logError("Ping error in " + JudgeHandlerVariables.name, e);
        }
    }

    public ObjectNode onPingResponse(ObjectNode packet) {
        return null;

        // Handle ping response packet
    }

    private void freeSelf(ObjectNode packet) {
        judgeList.onJudgeFree();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubmissionData {
        private Double time;

        private Integer memory;

        private Boolean shortCircuit;

        private boolean pretests_only;
    }

    private void send(ObjectNode request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String requestJson = mapper.writeValueAsString(request);
            System.out.println("Send submission: " + requestJson);
            byte[] compressedRequest = ZlibCompression.zlibify(requestJson);
            ByteBuf buffer = Unpooled.wrappedBuffer(compressedRequest);

            // Get the channel from the stored ChannelHandlerContext
//            Channel channel = this.ctx.channel();
            ChannelFuture future;
            for (Channel channel : channelGroup) {
                future = channel.writeAndFlush(buffer);
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("Request sent successfully");
                        } else {
                            System.out.println("Failed to send request");
                            // Handle the failure case
                        }
                    }
                });
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the JSON processing exception
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException, DataFormatException {
        channelGroup.add(ctx.channel());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = JsonNodeFactory.instance.objectNode();

        ByteBuf buf2 = (ByteBuf) msg;
        byte[] compressedData = new byte[buf2.readableBytes()];
        buf2.readBytes(compressedData);

        String decompressedData = ZlibCompression.dezlibify(compressedData, true);
        System.out.println("Received from client: " + decompressedData);
//        System.out.println("Received from client: " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        try {
            String packetString = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
            JsonNode packet = mapper.readTree(decompressedData);

            Function<ObjectNode, ObjectNode> handler = this.handlers.get(packet.get("name").asText());
            result = handler.apply((ObjectNode) packet);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = JsonNodeFactory.instance.objectNode();
            result = null;
        } finally {
            if (result == null) {
                return;
            }
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
