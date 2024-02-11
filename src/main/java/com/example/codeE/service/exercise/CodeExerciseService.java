package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;

import java.util.Optional;

public interface CodeExerciseService {
    CodeExercise createCodeExercise (CodeExercise codeExercise);

    Optional<CodeExercise> getCodeExerciseById(String exerciseId);

    void deleteCodeExerciseById(String exerciseId);
}
