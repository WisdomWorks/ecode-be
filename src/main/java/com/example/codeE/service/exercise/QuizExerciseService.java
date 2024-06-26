package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.request.exercise.quiz.QuizDetailResponse;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface QuizExerciseService {
    public QuizExercise createQuizExercise(QuizExercise quizExercise);
    public QuizExercise getQuizExerciseById(String exerciseId);
    public QuizQuestion getQuizQuestionByQuestionId(String questionId);
    public QuizExercise updateQuizExercise(String exerciseId, UpdateQuizExerciseRequest updateExercise);
    public void deleteQuizExerciseById(String exerciseId);
    QuizDetailResponse getQuizExerciseDetail(String exerciseId, HttpServletRequest request);
}
