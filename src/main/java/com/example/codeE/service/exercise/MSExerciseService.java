package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.MSExercise;

import java.util.List;

public interface MSExerciseService {
    MSExercise saveExerciseToMySql(MSExercise msExercise);

    List<MSExercise> getAllExercisesByCourseId(String courseId);

    void deleteExerciseInMySql(String exerciseId);
}
