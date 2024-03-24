package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExerciseWBD;

public interface CodeExerciseServiceWBD {
    CodeExerciseWBD createCodeExercise (CodeExerciseWBD codeExerciseWBD);

    CodeExerciseWBD getCodeExerciseById(String exerciseId);

    void deleteCodeExerciseById(String exerciseId);

    CodeExerciseWBD updateCodeExercise(CodeExerciseWBD codeExerciseWBD);
}
