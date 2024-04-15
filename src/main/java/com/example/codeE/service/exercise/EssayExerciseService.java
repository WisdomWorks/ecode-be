package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.request.exercise.essay.EssayDetailResponse;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface EssayExerciseService {
    EssayExercise createEssayExercise (EssayExercise essayExercise);
    EssayExercise getEssayExerciseById(String exerciseId);
    void deleteEssayExerciseById(String exerciseId);
    EssayExercise updateEssayExercise(String exerciseId,UpdateEssayExerciseRequest updateRequest);
    EssayDetailResponse getEssayExerciseDetail(String exerciseId, HttpServletRequest request);
}
