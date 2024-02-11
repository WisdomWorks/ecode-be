package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;

import java.util.Optional;

public interface CodeExerciseService {
    public CodeExercise createCodeExercise (CodeExercise codeExercise);

    public Optional<CodeExercise> getCodeExerciseById(String exerciseId);
}
