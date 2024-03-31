package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.request.exercise.quiz.QuizSubmissionsResponse;

import java.util.List;

public interface QuizSubmissionService {
    QuizSubmission createSubmission(QuizSubmission quizSubmission);
    List<QuizSubmission> getQuizSubmissionByExerciseId(String exerciseId);
    List<QuizSubmissionsResponse> getQuizSubmissionsByExerciseId(String exerciseId);
    QuizSubmission getStudentQuizSubmission(String submissionId);
    List<QuizSubmission> getQuizSubmissionByUserId(String exerciseId, String userId);
    float gradeSubmission(List<QuizAnswers> quizSubmission, List<QuizQuestion> quizExercise);
}
