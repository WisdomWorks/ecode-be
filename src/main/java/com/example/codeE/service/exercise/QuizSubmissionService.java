package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizQuestion;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuizSubmissionService {
    public QuizSubmission createSubmission(QuizSubmission quizSubmission);
    List<QuizSubmission> getQuizSubmissionByExerciseId(String exerciseId);
    QuizSubmission getStudentQuizSubmission(String submissionId);
    List<QuizSubmission> getQuizSubmissionByUserId(String exerciseId, String userId);
    public float gradeSubmission(List<QuizAnswers> quizSubmission,List<QuizQuestion>  quizExercise);
}
