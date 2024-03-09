package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;

public interface QuizExerciseService {
    public QuizExercise createQuizExercise(QuizExercise quizExercise);
    public QuizExercise getQuizExerciseById(String exerciseId);
    public QuizQuestion getQuizQuestionByQuestionId(String questionId);
    public QuizExercise updateQuizExercise(String exerciseId, UpdateQuizExerciseRequest updateExercise);
}
