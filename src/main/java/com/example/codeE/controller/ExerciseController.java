package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.common.TestCase;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.ExerciseService;
import com.example.codeE.service.exercise.common.TestcaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CreateCodeExerciseRequest request){
        List<String> testcaseIds = new ArrayList<>();
        for(TestCase tc: request.getTestcases()){
            String id = this.testcaseService.createTestcase(tc).getTestcaseId();
            testcaseIds.add(id);
        }
        CodeExercise codeExercise = this.codeExerciseService.createCodeExercise(
                new CodeExercise(request.getTopicId(), request.getExerciseName(), request.getKey(),
                        request.getStartTime(), request.getEndTime(), request.getType(), request.getPublicGroupIds(),
                        request.getLanguage(), request.getFunctionName(), request.getTemplate(),
                        request.getDescription(), testcaseIds)
        );
        this.exerciseService.saveExercise((Exercise) codeExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(codeExercise);
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllExerciseByCourseId(@RequestParam String courseId) {
        List<Exercise> exercises = this.exerciseService.getExercisesByCourseId(courseId);
//        List<CodeExercise> codeExercises = new ArrayList<>();
//        for(Exercise exercise: exercises) {
//            switch (exercise.getType()) {
//                case "code":
//                    codeExercises.add(this.codeExerciseService.getCodeExerciseById(exercise.getExerciseId()));
//                    break;
//            }
//        }
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    @DeleteMapping
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @ModelAttribute DeleteExerciseRequest request) {
        switch (request.getType()) {
            case "code":
                this.codeExerciseService.deleteCodeExerciseById(request.getExerciseId());
                break;
        }
        this.exerciseService.deleteExerciseById(request.getExerciseId());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExercise exercise) {
        return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.updateCodeExercise(exercise));
    }
}
