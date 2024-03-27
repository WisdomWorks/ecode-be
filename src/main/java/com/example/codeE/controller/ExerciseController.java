package com.example.codeE.controller;

import com.example.codeE.model.exercise.*;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizSubmissionRequest;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import com.example.codeE.service.exercise.*;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CreateCodeExerciseRequest request){
//        List<String> testcaseIds = new ArrayList<>();
//        for(TestCase tc: request.getTestcases()){
//            String id = this.testcaseService.createTestcase(tc).getTestcaseId();
//            testcaseIds.add(id);
//        }
//        CodeExerciseWBD codeExerciseWBD = this.codeExerciseService.createCodeExercise(
//                new CodeExerciseWBD(request.getTopicId(), request.getExerciseName(), request.getKey(),
//                        request.getStartTime(), request.getEndTime(), request.getType(), request.getPublicGroupIds(),
//                        request.getLanguage(), request.getFunctionName(), request.getTemplate(),
//                        request.getDescription(), testcaseIds)
//        );
//        this.exerciseService.saveExercise((Exercise) codeExerciseWBD);
//        return ResponseEntity.status(HttpStatus.CREATED).body(codeExerciseWBD);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Api is building");
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
    public ResponseEntity<?> getAllExerciseByCourseId(@RequestParam String courseId) {
        List<Exercise> exercises = this.exerciseService.getExercisesByCourseId(courseId);
//        List<CodeExercise> codeExercises = new ArßrayList<>();
//        for(Exercise exercise: exercises) {
//            switch (exercise.getType()) {
//                case "code":
//                    codeExercises.add(this.codeExerciseService.getCodeExerciseById(exercise.getExerciseId()));
//                    break;
//            }
//        }
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    @GetMapping
    @RequestMapping(value = "{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExerciseById(@PathVariable String exerciseId){
        System.out.println(exerciseId);
        Exercise exercise = this.exerciseService.getExerciseById(exerciseId);
        return switch (exercise.getType()) {
            case "quiz" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.quizExerciseService.getQuizExerciseById(exerciseId));
            case "essay" ->
                    ResponseEntity.status(HttpStatus.OK).body(this.essayExerciseService.getEssayExerciseById(exerciseId));
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        };
    }
    @GetMapping
    @RequestMapping(value = "detail/{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExerciseDetail(@PathVariable String exerciseId, @RequestParam String key){
        Exercise exercise = this.exerciseService.getDetailExercise(exerciseId, key);
        return switch (exercise.getType()){
            case "quiz" ->
                ResponseEntity.status(HttpStatus.OK).body(this.quizExerciseService.getQuizExerciseDetail(exerciseId));
            case "essay" ->
                ResponseEntity.status(HttpStatus.OK).body(this.essayExerciseService.getEssayExerciseDetail(exerciseId));
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        };
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
            case "code" -> this.codeExerciseService.deleteCodeExerciseById(exerciseId);
            case "quiz" -> this.quizExerciseService.deleteQuizExerciseById(exerciseId);
            case "essay" -> this.essayExerciseService.deleteEssayExerciseById(exerciseId);
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Something went wrong, type must be quiz/essay/code"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Delete success"));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExerciseWBD exercise) {
        return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.updateCodeExercise(exercise));
    }

    @PutMapping
    @RequestMapping(value = "quiz", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuizExercise(@RequestParam String exerciseId, @RequestBody UpdateQuizExerciseRequest request){
        QuizExercise updatedExercise = this.quizExerciseService.updateQuizExercise(exerciseId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }
    @PutMapping
    @RequestMapping(value = "essay", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEssayExercise(@RequestParam String exerciseId, @RequestBody UpdateEssayExerciseRequest request){
        EssayExercise updatedExercise = this.essayExerciseService.updateEssayExercise(exerciseId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }
    @PostMapping
    @RequestMapping(value = "view", method = RequestMethod.POST)
    public ResponseEntity<?> addPermissionExercise(@RequestBody CreatePermissionExerciseRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.modifiedPermission(request));
    }
}
