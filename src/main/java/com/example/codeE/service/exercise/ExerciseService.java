package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise saveExercise(Exercise exercise);

    List<Exercise> getExercisesByCourseId(String courseId);

    void deleteExerciseById(String exerciseId);
}
