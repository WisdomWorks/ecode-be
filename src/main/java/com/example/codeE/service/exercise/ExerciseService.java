package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;

import java.util.List;

public interface ExerciseService {
    Exercise saveQuizExercise(QuizExercise exercise);
    Exercise saveEsayExercise(EssayExercise exercise);
    List<Exercise> getExercisesByCourseId(String courseId);
    Exercise getExerciseById(String exerciseId);
    Exercise getDetailExercise(String exerciseId, String key);
    void deleteExerciseById(String exerciseId);
    List<ExerciseResponse> getExercisesByTopicId(String topicId);
    List<ExerciseResponse> getExercisesByUserId(String topicId, String userId);
    ExerciseResponse modifiedPermission(CreatePermissionExerciseRequest request);
}
