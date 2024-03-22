package com.example.codeE.controller;

import com.example.codeE.model.exercise.*;
import com.example.codeE.model.exercise.common.TestCase;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import com.example.codeE.service.exercise.*;
import com.example.codeE.service.exercise.common.TestcaseService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private TestcaseService testcaseService;

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
        List<String> testcaseIds = new ArrayList<>();
        for(TestCase tc: request.getTestcases()){
            String id = this.testcaseService.createTestcase(tc).getTestcaseId();
            testcaseIds.add(id);
        }
        CodeExerciseWBD codeExerciseWBD = this.codeExerciseService.createCodeExercise(
                new CodeExerciseWBD(request.getTopicId(), request.getExerciseName(), request.getKey(),
                        request.getStartTime(), request.getEndTime(), request.getType(), request.getPublicGroupIds(),
                        request.getLanguage(), request.getFunctionName(), request.getTemplate(),
                        request.getDescription(), testcaseIds)
        );
        this.exerciseService.saveExercise((Exercise) codeExerciseWBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(codeExerciseWBD);
    }

    @PostMapping
    @RequestMapping(value = "quiz", method = RequestMethod.POST)
    public ResponseEntity<?> createQuizExercise(@Valid @RequestBody QuizExercise request){
        this.exerciseService.saveExercise((Exercise) request);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizExerciseService.createQuizExercise(request));
    }

    @PostMapping
    @RequestMapping(value = "essay", method = RequestMethod.POST)
    public ResponseEntity<?> createEssayExercise(@Valid @RequestBody CreateEssayExerciseRequest request){
        EssayExercise essayExercise = new EssayExercise(request);
        this.exerciseService.saveExercise((Exercise) essayExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.essayExerciseService.createEssayExercise(essayExercise));
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
    @RequestMapping(value = "exercise", method = RequestMethod.GET)
    public ResponseEntity<?> getExerciseById(@RequestParam String exerciseId){
        Exercise exercise = this.exerciseService.getExerciseById(exerciseId);

        switch (exercise.getType()){
            case "quiz":
                return ResponseEntity.status(HttpStatus.OK).body(this.quizExerciseService.getQuizExerciseById(exerciseId));
            case "essay":
                return ResponseEntity.status(HttpStatus.OK).body(this.essayExerciseService.getEssayExerciseById(exerciseId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @PostMapping
    @RequestMapping(value = "quiz/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitQuizExercise(@Valid @RequestBody QuizSubmission quizSubmission){
        QuizExercise quizExercise = this.quizExerciseService.getQuizExerciseById(quizSubmission.getExerciseId());
        float score = this.quizSubmissionService.gradeSubmission(quizSubmission, quizExercise);
        quizSubmission.setScore(score);
        QuizSubmission submission = this.quizSubmissionService.createSubmission(quizSubmission);
        return ResponseEntity.status(HttpStatus.OK).body(submission);
    }

    @PostMapping
    @RequestMapping(value = "essay/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitEssayExercise(@Valid @RequestBody EssaySubmission essaySubmission){
        return ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.createSubmission(essaySubmission));
    }

    @PatchMapping
    @RequestMapping(value = "essay/grade", method = RequestMethod.PATCH)
    public ResponseEntity<?> gradeEssaySubmission(@RequestParam String essaySubmissionId, @RequestParam float score){
        return ResponseEntity.status(HttpStatus.OK).body(this.essaySubmissionService.gradeSubmission(essaySubmissionId, score));
    }

    @DeleteMapping
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @ModelAttribute DeleteExerciseRequest request) {
        switch (request.getType()) {
            case "code":
                this.codeExerciseService.deleteCodeExerciseById(request.getExerciseId());
                break;
            case "quiz":
                this.quizExerciseService.deleteQuizExerciseById(request.getExerciseId());
                break;
        }
        this.exerciseService.deleteExerciseById(request.getExerciseId());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExerciseWBD exercise) {
        return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.updateCodeExercise(exercise));
    }

    @PatchMapping
    @RequestMapping(value = "quiz", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateQuizExercise(@RequestParam String exerciseId, @RequestBody UpdateQuizExerciseRequest request){
        QuizExercise updatedExercise = this.quizExerciseService.updateQuizExercise(exerciseId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExercise);
    }
}
