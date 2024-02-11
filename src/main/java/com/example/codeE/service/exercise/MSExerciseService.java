package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.MSExercise;

import java.util.List;

public interface MSExerciseService {
    public MSExercise saveExerciseToMySql(MSExercise msExercise);

    public List<MSExercise> getAllExercisesByCourseId(String courseId);
}
