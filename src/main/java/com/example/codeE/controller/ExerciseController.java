package com.example.codeE.controller;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.AutoIncrement;
import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.model.exercise.*;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.model.user.User;
import com.example.codeE.request.course.ExportScoresRequest;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.GetDetailExerciseRequest;
import com.example.codeE.request.exercise.GradeSubmission;
import com.example.codeE.request.exercise.code.*;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import com.example.codeE.request.exercise.file.CreateFileExerciseRequest;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.UpdateFileExerciseRequest;
import com.example.codeE.request.exercise.quiz.*;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.exercise.*;
import com.example.codeE.service.exercise.common.SessionExerciseService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.problem.CodeExerciseTestcaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import com.example.codeE.service.exercise.submission.FileSubmissionService;
import com.example.codeE.service.exercise.submission.QuizSubmissionService;
import com.example.codeE.service.judge.JudgeService;
import com.example.codeE.service.user.UserService;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/exercises")
@Validated
public class ExerciseController {
    @Autowired
    private CodeExerciseService codeExerciseService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private FileExerciseService fileExerciseService;
    @Autowired
    private FileSubmissionService fileSubmissionService;
    @Autowired
    private QuizExerciseService quizExerciseService;
    @Autowired
    private QuizSubmissionService quizSubmissionService;
    @Autowired
    private EssayExerciseService essayExerciseService;
    @Autowired
    private EssaySubmissionService essaySubmissionService;

    @Autowired
    private CodeSubmissionService codeSubmissionService;

    @Autowired
    private SubmissionTestCaseService submissionTestCaseService;

    @Autowired
    private CodeExerciseTestcaseService codeExerciseTestcaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CourseService courseService;
    @Autowired
    private SessionExerciseService sessionExerciseService;

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CreateCodeExerciseRequest request){
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setTopicId(request.getTopicId());
        codeExercise.setExerciseName(request.getExerciseName());
        codeExercise.setKey(request.getKey());
        codeExercise.setStartTime(request.getStartTime());
        codeExercise.setEndTime(request.getEndTime());
        codeExercise.setDurationTime(request.getDurationTime());
        codeExercise.setShowAll(false);
        codeExercise.setReAttempt(request.getReAttempt());
        codeExercise.setPublicGroupIds(new ArrayList<String>());
        codeExercise.setDescription(request.getDescription());
        codeExercise.setTimeLimit((double) Constant.PROBLEM_MAX_TIME_LIMIT);
        codeExercise.setMemoryLimit(Constant.PROBLEM_MAX_MEMORY_LIMIT);
        codeExercise.setAllowedLanguageIds(request.getAllowedLanguageIds());
        codeExercise.setPoints(request.getPoints());
        codeExercise.setType("code");
        codeExercise.setUsingAiGrading(request.isUsingAiGrading());

        CodeExercise savedCodeExercise = codeExerciseService.createCodeExercise(codeExercise);
        this.exerciseService.saveCodeExercise(savedCodeExercise);

        List<TestCase> testCases = request.getTestCases();
        for(int i=0; i<testCases.size(); i++){
            testCases.get(i).setExerciseId(savedCodeExercise.getExerciseId());
            TestCase savedTestcase = this.codeExerciseTestcaseService.saveTestCase(testCases.get(i));
            testCases.get(i).setTestcaseId(savedTestcase.getTestcaseId());
        }

        savedCodeExercise.setTestCases(request.getTestCases());
        codeExerciseService.createCodeExercise(savedCodeExercise);
        this.exerciseService.saveCodeExercise(savedCodeExercise);

        codeExerciseService.createProblemFolder(request.getTestCases(), savedCodeExercise.getExerciseId());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Create success"));
    }

    @PostMapping
    @RequestMapping(value = "quiz", method = RequestMethod.POST)
    public ResponseEntity<?> createQuizExercise(@Valid @RequestBody CreateQuizExerciseRequest request){
        QuizExercise quizExercise = new QuizExercise(request);
        var quizSave = this.quizExerciseService.createQuizExercise(quizExercise);
        this.exerciseService.saveQuizExercise(quizSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizSave);
    }

    @PostMapping
    @RequestMapping(value = "quiz/excel", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createQuizFromExcel(@Valid @ModelAttribute CreateQuizExerciseByExcelRequest request, @RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExcelResult excelResult = ExcelHelper.readQuizQuestionsFromExcel(file);
            if (excelResult.getFailedRows().isEmpty()) {
                List<QuizQuestion> questions = excelResult.getQuestions();
                QuizExercise quizExercise = new QuizExercise(request, questions);
                quizExerciseService.createQuizExercise(quizExercise);
                exerciseService.saveQuizExercise(quizExercise);
                response.put("message", "Quiz exercise created successfully");
                return ResponseEntity.ok().body(response);
            } else {
                List<Integer> failedRows = excelResult.getFailedRows();
                response.put("message", "Failed rows found");
                response.put("failedRows", failedRows);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error creating quiz from Excel file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping
    @RequestMapping(value = "essay", method = RequestMethod.POST)
    public ResponseEntity<?> createEssayExercise(@Valid @RequestBody CreateEssayExerciseRequest request){
        EssayExercise essayExercise = new EssayExercise(request);
        var essaySave = this.essayExerciseService.createEssayExercise(essayExercise);
        this.exerciseService.saveEsayExercise(essaySave);
        return ResponseEntity.status(HttpStatus.CREATED).body(essaySave);
    }

    @PostMapping
    @RequestMapping(value = "file", method = RequestMethod.POST)
    public ResponseEntity<?> createFileExercise(@Valid @RequestBody CreateFileExerciseRequest request){
        FileExercise fileExercise = new FileExercise(request);
        var fileSave = this.fileExerciseService.createFileExercise(fileExercise);
        this.exerciseService.saveFileExercise(fileSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(fileSave);
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public  ResponseEntity<?> getAllExerciseByCourseId(@RequestParam String courseId) {
        List<ExerciseResponse> exercises = this.exerciseService.getExercisesByCourseId(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    @GetMapping
    @RequestMapping(value = "{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExerciseById(@PathVariable String exerciseId){
        Exercise exercise = this.exerciseService.getExerciseById(exerciseId);
        return  ResponseEntity.status(HttpStatus.OK).body(exercise);
    }

    @PostMapping
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public ResponseEntity<?> getExerciseDetail(@RequestBody GetDetailExerciseRequest requestGetDetail, HttpServletRequest request, HttpServletResponse response) {
        Exercise exercise = this.exerciseService.getDetailExercise(requestGetDetail.getExerciseId(), requestGetDetail.getKey(), requestGetDetail.getStudentId(), requestGetDetail.getUserUrgent(), request, response);
        if (exercise == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Student can not enroll exercise now."));
        }
        switch (exercise.getType()){
            case "code" :
                return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.getCodeExerciseDetail(requestGetDetail.getExerciseId(), request));
            case "quiz":
                return ResponseEntity.status(HttpStatus.OK).body(this.quizExerciseService.getQuizExerciseDetail(requestGetDetail.getExerciseId(), request));
            case "essay":
                return ResponseEntity.status(HttpStatus.OK).body(this.essayExerciseService.getEssayExerciseDetail(requestGetDetail.getExerciseId(), request));
            case "file":
                return ResponseEntity.status(HttpStatus.OK).body(this.fileExerciseService.getFileExerciseDetail(requestGetDetail.getExerciseId()));
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        }
    }

    @PostMapping
    @RequestMapping(value = "code/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitCodeExercise(@Valid @RequestBody SubmitCodeExerciseRequest requestSubmit, HttpServletRequest request, HttpServletResponse response) {
        MongoDatabase database = mongoTemplate.getDb();
        AutoIncrement autoIncrement = new AutoIncrement(database);

        try{
            CodeSubmission submission = new CodeSubmission(judgeService);
            submission.setSubmissionId(String.valueOf(autoIncrement.getNextSequence("code_submission")));
            submission.setExerciseId(requestSubmit.getExerciseId());
            submission.setLanguageId(requestSubmit.getLanguageId());
            submission.setSource(requestSubmit.getSource());
            submission.setStudentId(requestSubmit.getStudentId());
            submission.setPretested(false);

            CodeExercise codeExercise = this.codeExerciseService.getCodeExerciseById(requestSubmit.getExerciseId());
            submission.setTime(codeExercise.getTimeLimit());
            submission.setMemory(codeExercise.getMemoryLimit());
            submission.setLockedAfter(codeExercise.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            CodeSubmission savedSubmission = codeSubmissionService.saveCodeSubmission(submission);
            savedSubmission.judge(false, false);
            sessionExerciseService.removeSession(response, request);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("isSuccess", true));
    }

    @PostMapping
    @RequestMapping(value = "code/run", method = RequestMethod.POST)
    public ResponseEntity<?> runCodeExercise(@Valid @RequestBody CodeRunRequest request){
        MongoDatabase database = mongoTemplate.getDb();
        AutoIncrement autoIncrement = new AutoIncrement(database);
        String submissionId = String.valueOf(autoIncrement.getNextSequence("code_submission"));
        try{
            CodeSubmission submission = new CodeSubmission(judgeService);
            submission.setSubmissionId(submissionId);
            submission.setExerciseId(request.getExerciseId());
            submission.setLanguageId(request.getLanguageId());
            submission.setSource(request.getSource());
            submission.setStudentId(request.getStudentId());
            submission.setPretested(true);

            CodeExercise codeExercise = this.codeExerciseService.getCodeExerciseById(request.getExerciseId());
            submission.setTime(codeExercise.getTimeLimit());
            submission.setMemory(codeExercise.getMemoryLimit());
            submission.setLockedAfter(codeExercise.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            CodeSubmission savedSubmission = codeSubmissionService.saveCodeSubmission(submission);
            savedSubmission.judge(false, false);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("submissionId", submissionId));
    }

    @GetMapping
    @RequestMapping(value = "code/run/{submissionId}", method = RequestMethod.GET)
    public ResponseEntity<?> runCodeExercise(@PathVariable String submissionId, @RequestParam String userId){
        CodeSubmission submission = this.codeSubmissionService.getCodeSubmissionById(submissionId);

        RunCodeExerciseResponse response = new RunCodeExerciseResponse();
        String status = submission.getStatus();
        response.setStatus(status);
        if (status.equals("CE") || status.equals("IE")){
            response.setMessage(submission.getError());
            response.setTestCases(new ArrayList<>());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        User user = this.userService.getUserByUserId(userId);
        if (user.getRole().equals("teacher")){
            response.setTestCases(this.submissionTestCaseService.findBySubmissionId(submissionId));
        } else {
            response.setTestCases(this.submissionTestCaseService.getAllTcBySubmissionId(submissionId));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @RequestMapping(value = "quiz/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitQuizExercise(@Valid @RequestBody CreateQuizSubmissionRequest quizSubmission, HttpServletRequest request, HttpServletResponse response) {
        QuizExercise quizExercise = this.quizExerciseService.getQuizExerciseById(quizSubmission.getExerciseId());
        float score = this.quizSubmissionService.gradeSubmission(quizSubmission.getSubmission(), quizExercise.getQuestions());
        var submission = new QuizSubmission(quizSubmission, score);
        QuizSubmission responseExercise = this.quizSubmissionService.createSubmission(submission);
        sessionExerciseService.removeSession(response, request);
        return ResponseEntity.status(HttpStatus.OK).body(responseExercise);
    }

    @PostMapping
    @RequestMapping(value = "essay/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitEssayExercise(@Valid @RequestBody CreateEssaySubmissionRequest essaySubmission, HttpServletRequest request, HttpServletResponse response) {
        var essayExercise = this.essaySubmissionService.createSubmission(essaySubmission);
        sessionExerciseService.removeSession(response, request);
        return ResponseEntity.status(HttpStatus.OK).body(essayExercise);
    }

    @PostMapping
    @RequestMapping(value = "file/submit", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> submitFileExercise(@Valid @ModelAttribute CreateFileSubmissionRequest request, @RequestPart(required = false) MultipartFile file){
        if (file == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Please upload your file"));
        }
        FileSubmission result = this.fileSubmissionService.createSubmission(request, file);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    @RequestMapping(value = "preview/{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPreviewExercise(@PathVariable String exerciseId, @RequestParam String studentId){
        Exercise exercise = this.exerciseService.getExerciseById(exerciseId);
        if (Objects.equals(exercise.getType(), "file")){
            return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.getFilePreviewExercise(exerciseId, studentId));
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.getPreviewExercise(exerciseId, studentId));
    }

    @PutMapping
    @RequestMapping(value = "essay/grade", method = RequestMethod.PUT)
    public ResponseEntity<?> gradeEssaySubmission(@RequestBody GradeSubmission request){
        return ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.gradeSubmission(request.getSubmissionId(), request.getScore(), request.getComment()));
    }
    @PutMapping
    @RequestMapping(value = "code/grade", method = RequestMethod.PUT)
    public ResponseEntity<?> gradeCodeSubmission(@RequestBody GradeSubmission request){
        return ResponseEntity.status(HttpStatus.OK).body(this.codeSubmissionService.GradeCodeSubmission(request.getSubmissionId(), request.getScore(), request.getComment()));
    }
    @PutMapping
    @RequestMapping(value = "file/grade", method = RequestMethod.PUT)
    public ResponseEntity<?> gradeFileSubmission(@RequestBody GradeSubmission request){
        return ResponseEntity.status(HttpStatus.OK).body(this.fileSubmissionService.gradeSubmission(request.getSubmissionId(), request.getScore(), request.getComment()));
    }
    @DeleteMapping
    @RequestMapping(value = "{exerciseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @PathVariable String exerciseId, @Valid @RequestParam String type) {
        this.exerciseService.deleteExerciseById(exerciseId);
        switch (type) {
            case "quiz" -> this.quizExerciseService.deleteQuizExerciseById(exerciseId);
            case "essay" -> this.essayExerciseService.deleteEssayExerciseById(exerciseId);
            case "code" -> this.codeExerciseService.deleteCodeExercise(exerciseId);
            case "file" -> this.fileExerciseService.deleteFileExerciseById(exerciseId);
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Delete success"));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@RequestBody UpdateCodeExerciseRequest request) {
        CodeExercise updatedExercise = this.codeExerciseService.updateCodeExercise(request.getExerciseId(), request);

         List<TestCase> testCases = request.getTestCases();
         for(int i=0; i<testCases.size(); i++){
             if (testCases.get(i).getTestcaseId() == null){
                 testCases.get(i).setExerciseId(updatedExercise.getExerciseId());
                 TestCase savedTestcase = this.codeExerciseTestcaseService.saveTestCase(testCases.get(i));
                 testCases.get(i).setTestcaseId(savedTestcase.getTestcaseId());
             }
         }

         updatedExercise.setTestCases(request.getTestCases());
         codeExerciseService.createCodeExercise(updatedExercise);
         this.exerciseService.saveCodeExercise(updatedExercise);

         codeExerciseService.createProblemFolder(request.getTestCases(), updatedExercise.getExerciseId());
         return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
     }

    @PutMapping
    @RequestMapping(value = "quiz", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuizExercise(@RequestBody UpdateQuizExerciseRequest request){
        QuizExercise updatedExercise = this.quizExerciseService.updateQuizExercise(request.getExerciseId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }

    @PutMapping
    @RequestMapping(value = "essay", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEssayExercise(@RequestBody UpdateEssayExerciseRequest request){
        EssayExercise updatedExercise = this.essayExerciseService.updateEssayExercise(request.getExerciseId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }

    @PutMapping
    @RequestMapping(value = "file", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFileExercise(@RequestBody UpdateFileExerciseRequest request){
        FileExercise updatedExercise = this.fileExerciseService.updateFileExercise(request.getExerciseId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }

    @PostMapping
    @RequestMapping(value = "view", method = RequestMethod.POST)
    public ResponseEntity<?> addPermissionExercise(@RequestBody CreatePermissionExerciseRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.modifiedPermission(request));
    }

    @GetMapping
    @RequestMapping(value = "{exerciseId}/all-submission", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSubmissionByExerciseId(@PathVariable String exerciseId, @RequestParam String type) {
        try{
            return switch (type) {
                case "quiz" ->
                        ResponseEntity.status(HttpStatus.OK).body(this.quizSubmissionService.getQuizSubmissionsByExerciseId(exerciseId));
                case "essay" ->
                        ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.getEssaySubmissionsByExerciseId(exerciseId));
                case "code" ->
                        ResponseEntity.status(HttpStatus.OK).body(this.codeSubmissionService.getCodeSubmissionsByExerciseId(exerciseId));
                case "file" ->
                        ResponseEntity.status(HttpStatus.OK).body(this.fileSubmissionService.getFileSubmissionsByExerciseId(exerciseId));
                default ->
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
            };
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
    }

    @GetMapping
    @RequestMapping(value = "submit/{submissionId}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentSubmission(@PathVariable String submissionId, @RequestParam String type) {
        return switch (type) {
            case "quiz" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.quizSubmissionService.getStudentQuizSubmission(submissionId));
            case "essay" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.getEssaySubmission(submissionId));
            case "code" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.codeSubmissionService.getCodeSubmissionResponseById(submissionId));
            case "file" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.fileSubmissionService.getFileSubmissionById(submissionId));
            default ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
        };
    }

    @GetMapping
    @RequestMapping(value = "all-submission/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudentSubmission(@PathVariable String userId, @RequestParam String courseId){
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.getAllStudentSubmission(courseId, userId));
    }

    @GetMapping
    @RequestMapping(value = "submit/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExerciseByUserId(@RequestParam String exerciseId, @PathVariable String userId, @RequestParam String type) {
        return switch (type) {
            case "quiz" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.quizSubmissionService.getQuizSubmissionByUserId(exerciseId, userId));
            case "essay" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.getEssaySubmissionByUserId(exerciseId, userId));
            case "code" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.codeSubmissionService.getCodeSubmissionByUserId(exerciseId, userId));
            case "file" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.fileSubmissionService.getFileSubmissionByUserId(exerciseId, userId));
            default ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
        };
    }

    @PostMapping
    @RequestMapping(value = "export-scores", method = RequestMethod.POST)
    public ResponseEntity<Object> exportScores(@Valid @RequestBody ExportScoresRequest request, HttpServletResponse response){
        try {
            String courseId = request.getCourseId();
            // Get the list of exercises for the specified course
            List<Exercise> exercises = exerciseService.getAllExerciseInCourse(courseId);

            // Get all students in the course
            List<User> students = courseService.getStudentsByCourseId(courseId);

            // Call the ExcelHelper to export the student scores
            exerciseService.exportStudentScores(exercises, students, response);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            String errorMessage = "Error occurred while exporting scores: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", errorMessage));
        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", errorMessage));
        } catch (Exception e) {
            String errorMessage = "An unexpected error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", errorMessage));
        }
    }
}
