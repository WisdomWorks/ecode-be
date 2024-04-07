package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.*;
import com.example.codeE.request.exercise.AllStudentSubmissionResponse;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.ExerciseStudentResponse;

import java.util.List;

public interface ExerciseService {
    Exercise saveQuizExercise(QuizExercise exercise);
    Exercise saveEsayExercise(EssayExercise exercise);
    Exercise saveCodeExercise(CodeExercise exercise);
    Exercise saveFileExercise(FileExercise exercise);
    ExerciseStudentResponse getPreviewExercise(String exerciseId, String studentId);
    List<ExerciseResponse> getExercisesByCourseId(String courseId);
    Exercise getExerciseById(String exerciseId);
    Exercise getDetailExercise(String exerciseId, String key, String studentId);
    void deleteExerciseById(String exerciseId);
    List<ExerciseResponse> getExercisesByTopicId(String topicId);
    List<ExerciseResponse> getExercisesByUserId(String topicId, String userId);
    ExerciseResponse modifiedPermission(CreatePermissionExerciseRequest request);
    List<AllStudentSubmissionResponse> getAllStudentSubmission(String CourseId, String userId);
}
