package com.example.codeE.controller;

import com.example.codeE.helper.AutoIncrement;
import com.example.codeE.model.exercise.*;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.GetDetailExerciseRequest;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.request.exercise.code.SubmitCodeExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizSubmissionRequest;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import com.example.codeE.service.exercise.*;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import com.example.codeE.service.judge.JudgeService;
import com.mongodb.client.MongoDatabase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercises")
@Validated
public class ExerciseController {
    @Autowired
    private CodeExerciseService codeExerciseService;
    @Autowired
    private ExerciseService exerciseService;

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
    private JudgeService judgeService;

    @Autowired
    private MongoTemplate mongoTemplate;

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
        codeExercise.setShowAll(request.isShowAll());
        codeExercise.setReAttempt(request.getReAttempt());
        codeExercise.setPublicGroupIds(request.getPublicGroupIds());
        codeExercise.setDescription(request.getDescription());
        codeExercise.setTimeLimit(request.getTimeLimit());
        codeExercise.setMemoryLimit(request.getMemoryLimit());
        codeExercise.setAllowedLanguageIds(request.getAllowedLanguageIds());
        codeExercise.setPoints(request.getPoints());
        codeExercise.setType("code");

        CodeExercise savedCodeExercise = codeExerciseService.createCodeExercise(codeExercise);
        this.exerciseService.saveCodeExercise(savedCodeExercise);

        codeExerciseService.createProblemFolder(request.getTestCaseList(), savedCodeExercise.getExerciseId());
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
    @RequestMapping(value = "essay", method = RequestMethod.POST)
    public ResponseEntity<?> createEssayExercise(@Valid @RequestBody CreateEssayExerciseRequest request){
        EssayExercise essayExercise = new EssayExercise(request);
        var essaySave = this.essayExerciseService.createEssayExercise(essayExercise);
        this.exerciseService.saveEsayExercise(essaySave);
        return ResponseEntity.status(HttpStatus.CREATED).body(essaySave);
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
        System.out.println(exerciseId);
        Exercise exercise = this.exerciseService.getExerciseById(exerciseId);
        return  ResponseEntity.status(HttpStatus.OK).body(exercise);
    }
    @PostMapping
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public ResponseEntity<?> getExerciseDetail(@RequestBody GetDetailExerciseRequest request){
        Exercise exercise = this.exerciseService.getDetailExercise(request.getExerciseId(), request.getKey(), request.getStudentId());
        return switch (exercise.getType()){
            case "code" ->
                ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.getCodeExerciseById(request.getExerciseId()));
            case "quiz" ->
                ResponseEntity.status(HttpStatus.OK).body(this.quizExerciseService.getQuizExerciseDetail(request.getExerciseId()));
            case "essay" ->
                ResponseEntity.status(HttpStatus.OK).body(this.essayExerciseService.getEssayExerciseDetail(request.getExerciseId() ));
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        };
    }
    @PostMapping
    @RequestMapping(value = "code/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitCodeExercise(@Valid @RequestBody SubmitCodeExerciseRequest request){
        MongoDatabase database = mongoTemplate.getDb();
        AutoIncrement autoIncrement = new AutoIncrement(database);

        try{
            CodeSubmission submission = new CodeSubmission(judgeService);
            submission.setSubmissionId(String.valueOf(autoIncrement.getNextSequence("code_submission")));
            submission.setExerciseId(request.getExerciseId());
            submission.setLanguageId(request.getLanguageId());
            submission.setSource(request.getSource());
            submission.setStudentId(request.getStudentId());

            CodeExercise codeExercise = this.codeExerciseService.getCodeExerciseById(request.getExerciseId());
            submission.setTime(codeExercise.getTimeLimit());
            submission.setMemory(codeExercise.getMemoryLimit());
            submission.setLockedAfter(codeExercise.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            CodeSubmission savedSubmission = codeSubmissionService.saveCodeSubmission(submission);
            savedSubmission.judge(false, false);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("isSuccess", true));
    }

    @GetMapping
    @RequestMapping(value = "code/run/{submissionId}", method = RequestMethod.GET)
    public ResponseEntity<?> runCodeExercise(@PathVariable String submissionId){
        return ResponseEntity.status(HttpStatus.OK).body(this.submissionTestCaseService.getAllTcBySubmissionId(submissionId));
    }

    @PostMapping
    @RequestMapping(value = "quiz/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitQuizExercise(@Valid @RequestBody CreateQuizSubmissionRequest quizSubmission){
        QuizExercise quizExercise = this.quizExerciseService.getQuizExerciseById(quizSubmission.getExerciseId());
        float score = this.quizSubmissionService.gradeSubmission(quizSubmission.getSubmission(), quizExercise.getQuestions());
        var submission = new QuizSubmission(quizSubmission, score);
        QuizSubmission response = this.quizSubmissionService.createSubmission(submission);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @RequestMapping(value = "essay/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitEssayExercise(@Valid @RequestBody CreateEssaySubmissionRequest essaySubmission){
        return ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.createSubmission(essaySubmission));
    }
    @GetMapping
    @RequestMapping(value = "preview/{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPreviewExercise(@PathVariable String exerciseId){
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.getPreviewExercise(exerciseId));
    }

    @PutMapping
    @RequestMapping(value = "essay/grade", method = RequestMethod.PUT)
    public ResponseEntity<?> gradeEssaySubmission(@RequestParam String essaySubmissionId, @RequestParam float score){
        return ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.gradeSubmission(essaySubmissionId, score));
    }

    @DeleteMapping
    @RequestMapping(value = "{exerciseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @PathVariable String exerciseId, @Valid @RequestParam String type) {
        this.exerciseService.deleteExerciseById(exerciseId);
        switch (type) {
            case "quiz" -> this.quizExerciseService.deleteQuizExerciseById(exerciseId);
            case "essay" -> this.essayExerciseService.deleteEssayExerciseById(exerciseId);
            case "code" -> this.codeExerciseService.deleteCodeExercise(exerciseId);
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Delete success"));
    }

    // @PutMapping
    // @RequestMapping(value = "code", method = RequestMethod.PUT)
    // public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExerciseWBD exercise) {
    //     return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseServiceWBD.updateCodeExercise(exercise));
    // }

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
    public ResponseEntity<?> getStudentEssaySubmission(@PathVariable String submissionId, @RequestParam String type) {
        return switch (type) {
            case "quiz" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.quizSubmissionService.getStudentQuizSubmission(submissionId));
            case "essay" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.getEssaySubmissionByExerciseId(submissionId));
            case "code" -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("API not provide");
            default ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
        };
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
            default ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Something went wrong, type must be quiz/essay/code"));
        };
    }
}
