package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.request.exercise.ExerciseResponse;

import java.util.List;

public interface ExerciseService {
    Exercise saveExercise(Exercise exercise);
    List<Exercise> getExercisesByCourseId(String courseId);
    Exercise getExerciseById(String exerciseId);
    void deleteExerciseById(String exerciseId);
    List<ExerciseResponse> getExercisesByTopicId(String topicId);
    List<ExerciseResponse> getExercisesByUserId(String topicId, String userId);
}
