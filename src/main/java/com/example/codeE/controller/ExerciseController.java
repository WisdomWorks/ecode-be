package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.service.exercise.CodeExerciseImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
@Validated
public class ExerciseController {
    @Autowired
    private CodeExerciseImpl codeExerciseImpl;

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CodeExercise exercise){
        CodeExercise codeExercise = this.codeExerciseImpl.createCodeExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(codeExercise);
    }
}
