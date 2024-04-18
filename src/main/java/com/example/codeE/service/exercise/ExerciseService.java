package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.*;
import com.example.codeE.model.user.User;
import com.example.codeE.request.exercise.AllStudentSubmissionResponse;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.ExerciseStudentResponse;
import com.example.codeE.request.exercise.file.response.FilePreviewResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ExerciseService {
    Exercise saveQuizExercise(QuizExercise exercise);
    Exercise saveEsayExercise(EssayExercise exercise);
    Exercise saveCodeExercise(CodeExercise exercise);
    Exercise saveFileExercise(FileExercise exercise);
    ExerciseStudentResponse getPreviewExercise(String exerciseId, String studentId);
    FilePreviewResponse getFilePreviewExercise(String exerciseId, String studentId);
    List<ExerciseResponse> getExercisesByCourseId(String courseId);
    Exercise getExerciseById(String exerciseId);
    Exercise getDetailExercise(String exerciseId, String key, String studentId, String userUrgent, HttpServletRequest request, HttpServletResponse response);
    void deleteExerciseById(String exerciseId);
    List<ExerciseResponse> getExercisesByTopicId(String topicId);
    List<ExerciseResponse> getExercisesByUserId(String topicId, String userId);
    ExerciseResponse modifiedPermission(CreatePermissionExerciseRequest request);
    List<AllStudentSubmissionResponse> getAllStudentSubmission(String CourseId, String userId);
    String exportResultExercise(String exerciseId, String type);
    List<Exercise> getAllExerciseInCourse(String courseId);
    void exportStudentScores(List<Exercise> exercises, List<User> students, HttpServletResponse response) throws IOException;
    Float getLatestScoreByStudentAndExercise(String studentId, String exerciseId, String exerciseType);
}
