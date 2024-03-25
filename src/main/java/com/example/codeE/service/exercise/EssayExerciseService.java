package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.request.exercise.essay.EssayDetailResponse;

public interface EssayExerciseService {
    EssayExercise createEssayExercise (EssayExercise essayExercise);

    EssayExercise getEssayExerciseById(String exerciseId);

    void deleteEssayExerciseById(String exerciseId);

    EssayExercise updateEssayExercise(EssayExercise essayExercise);
    EssayDetailResponse getEssayExerciseDetail(String exerciseId);
}
