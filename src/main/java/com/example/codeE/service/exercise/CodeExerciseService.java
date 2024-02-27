package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;

public interface CodeExerciseService {
    CodeExercise createCodeExercise (CodeExercise codeExercise);

    CodeExercise getCodeExerciseById(String exerciseId);

    void deleteCodeExerciseById(String exerciseId);

    CodeExercise updateCodeExercise(CodeExercise codeExercise);

}
