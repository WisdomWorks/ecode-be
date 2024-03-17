package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.EssayExercise;

public interface EssayExerciseService {
    EssayExercise createEssayExercise (EssayExercise essayExercise);

    EssayExercise getEssayExerciseById(String exerciseId);

    void deleteEssayExerciseById(String exerciseId);

    EssayExercise updateEssayExercise(EssayExercise essayExercise);
}
