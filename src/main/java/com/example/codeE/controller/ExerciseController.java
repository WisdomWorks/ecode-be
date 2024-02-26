package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.ExerciseService;
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

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CodeExercise exercise){
        CodeExercise codeExercise = this.codeExerciseService.createCodeExercise(exercise);
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
