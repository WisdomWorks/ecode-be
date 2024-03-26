package com.example.codeE.judge.handlers;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.model.exercise.common.LanguageLimit;
import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.repository.CodeSubmissionRepository;
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
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
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
    HashMap<String, List<List<Object>>> executors;
    private String working;
    private String name;
    private boolean isDisabled;
    private String judgeAddress;
    private int timeout;

    @Autowired
    private RuntimeVersionService runtimeVersionService;

    public JudgeHandler(){
        this.judge = new Judge();
        this.problems = new ArrayList<>();
        this.executors = new HashMap<>();
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
        private static CodeSubmissionRepository codeSubmissionRepository;

        @Autowired
        private static CodeSubmissionService codeSubmissionService;

        @Autowired
        private static CodeExerciseService codeExerciseService;

        @Autowired
        private static LanguageLimitService languageLimitService;

        @Autowired
        private static SubmissionTestCaseService submissionTestCaseService;

        private static final JudgeHandler judgeHandler = new JudgeHandler();

        static {
            handlers = new HashMap<>();
            handlers.put("handshake", Handlers::onHandshake);
            handlers.put("submission-processing", Handlers::onSubmissionProcessing);
            handlers.put("submission-acknowledged", Handlers::onSubmissionAcknowledged);
            handlers.put("supported-problems", Handlers::onSupportedProblems);
            handlers.put("grading-begin", Handlers::onGradingBegin);
            handlers.put("grading-end", Handlers::onGradingEnd);
            handlers.put("compile-error", Handlers::onCompileError);
            handlers.put("compile-message", Handlers::onCompileMessage);
            handlers.put("internal-error", Handlers::onInternalError);
            handlers.put("submission-terminated", Handlers::onSubmissionTerminated);
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
                    judgeHandler.executors.keySet().stream().toList()
            );

            for (Map.Entry<String, List<List<Object>>> entry : judgeHandler.executors.entrySet()) {
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
                    judgeHandler.runtimeVersionService.saveRuntimeVersion(new RuntimeVersion(
                            languageId,
                            judgeHandler.judge.getJudgeId(),
                            name,
                            version,
                            0
                    ));
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
                judgeHandler.executors.put(key, value);
            }

            judgeHandler.name = packet.get("id").asText();

            ObjectNode response = JsonNodeFactory.instance.objectNode();
            response.put("name", "handshake-success");
            connected();
            return response;
        }

        public static ObjectNode submit(ObjectNode packet) {
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
                CodeSubmission submission = codeSubmissionService.getCodeSubmissionById(id);
                if (submission == null) {
                    LoggerHelper.logError("Submission vanished: " + id);
                    return null;
                }

                CodeExercise problem = codeExerciseService.getProblemById(submission.getExerciseId());
                String problemId = submission.getExerciseId();
                Double timeLimit = problem.getTimeLimit();
                Integer memoryLimit = problem.getMemoryLimit();
                Boolean shortCircuit = problem.getShortCircuit();
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
                LoggerHelper.logError("Something wrong with submission " + id);
                return null;
            }
        }

        public static ObjectNode onSubmissionProcessing(ObjectNode packet) {
            String id = packet.get("submission-id").asText();

            CodeSubmission submission = codeSubmissionService.getCodeSubmissionById(id);

            if (submission != null) {
                submission.setStatus("P");
                submission.setJudgedOn(judgeHandler.judge.getJudgeId());
                codeSubmissionService.updateCodeSubmission(submission);

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
                onSubmissionWrongAcknowledge(packet, working, submissionId);
            } else {
                LoggerHelper.logInfo("Submission acknowledged: " + working);
                onSubmissionProcessing(packet);
            }

            return packet;
        }

        public static void onSubmissionWrongAcknowledge(ObjectNode packet, String expected, String got) {
            codeSubmissionService.updateStatusAndResult(expected, "IE", "IE");
            codeSubmissionService.updateStatusAndResultBySubmissionIdAndStatus(got, "QU", "IE", "IE");
        }

        public static ObjectNode onSupportedProblems(ObjectNode packet) {
            LoggerHelper.logInfo(judgeHandler.name + ": Updated problem list" );
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

//            self.problems = dict(self._problems)
//            if not self.working:
//            self.judges.update_problems(self)
//
//            self.judge.problems.set(
//                    Problem.objects.filter(code__in=list(self.problems.keys()))
//            )
//            json_log.info(
//                    self._make_json_log(action="update-problems", count=len(self.problems))
//            )
            return null;
        }

        public static ObjectNode onGradingBegin(ObjectNode packet) {
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

        public static ObjectNode onGradingEnd(ObjectNode packet) {
            return null;

            // Handle grading end packet
        }

        public static ObjectNode onCompileError(ObjectNode packet) {
            String submissionId = packet.get("submission-id").asText();
//            String log = packet.get("log").asText();

            LoggerHelper.logInfo(judgeHandler.getName() + ": Submission failed to compile: " + submissionId);
            if (codeSubmissionService.getCodeSubmissionById(submissionId) != null) {
                codeSubmissionService.updateStatusAndResult(submissionId, "CE", "CE");
            } else {
                LoggerHelper.logError("Unknown submission: " + submissionId);
            }
            return packet;
        }

        public static ObjectNode onCompileMessage(ObjectNode packet) {
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

        public static ObjectNode onInternalError(ObjectNode packet) {
            String submissionId = packet.get("submission-id").asText();
            String message = packet.get("message").asText();

            try {
                throw new Exception("\n\n" + message);
            } catch (Exception e) {
                LoggerHelper.logError("Judge " + judgeHandler.getName() + " failed while handling submission " + submissionId, e);
            }
            if (codeSubmissionService.getCodeSubmissionById(submissionId) != null) {
                codeSubmissionService.updateStatusAndResult(submissionId, "IE", "IE");
            } else {
                LoggerHelper.logError("Unknown submission: " + submissionId);
            }
            return packet;
        }

        public static ObjectNode onSubmissionTerminated(ObjectNode packet) {
            String submissionId = packet.get("submission-id").asText();
            LoggerHelper.logInfo(judgeHandler.getName() + ": Submission aborted: " + submissionId);
            if(codeSubmissionService.getCodeSubmissionById(submissionId) != null) {
                codeSubmissionService.updateStatusAndResult(submissionId, "AB", "AB");
            } else {
                LoggerHelper.logError("Unknown submission: " + submissionId);
            }
            return null;
        }

        public static ObjectNode onTestCaseStatus(ObjectNode packet) {
            //log
            String submissionId = packet.get("submission-id").asText();
            List<SubmissionTestCase> updates = new ArrayList<>();
            JsonNode cases = packet.get("cases");
            if (cases.isArray()) {
                for (JsonNode testCase : cases) {
                    SubmissionTestCase submissionTestCase = new SubmissionTestCase();

                    submissionTestCase.setSubmissionId(submissionId);
                    int status = testCase.get("status").asInt();
                    switch (status) {
                        case 4:
                            submissionTestCase.setStatus("TLE");
                            break;
                        case 8:
                            submissionTestCase.setStatus("MLE");
                            break;
                        case 64:
                            submissionTestCase.setStatus("OLE");
                            break;
                        case 2:
                            submissionTestCase.setStatus("RTE");
                            break;
                        case 16:
                            submissionTestCase.setStatus("IR");
                            break;
                        case 1:
                            submissionTestCase.setStatus("WA");
                            break;
                        case 32:
                            submissionTestCase.setStatus("SC");
                            break;
                        default:
                            submissionTestCase.setStatus("AC");
                            break;
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

        public static ObjectNode onMalformedPacket(ObjectNode packet) {
            return null;

            // Handle malformed packet
        }

        public static ObjectNode onPingResponse(ObjectNode packet) {
            return null;

            // Handle ping response packet
        }

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SubmissionData {
            private String submissionDataId;

            private Double time;

            private Integer memory;

            private Boolean shortCircuit;

            private boolean pretests_only;

            public SubmissionData(Double time, Integer memory, Boolean shortCircuit, boolean isPretested) {
                this.time = time;
                this.memory = memory;
                this.shortCircuit = shortCircuit;
                this.pretests_only = isPretested;
            }
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
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
            byte[] compressed = ZlibCompression.zlibify(mapper.writeValueAsString(result));
//            ByteBuf buf = Unpooled.wrappedBuffer(mapper.writeValueAsString(result).getBytes());
            ByteBuf buf = Unpooled.wrappedBuffer(compressed);
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
