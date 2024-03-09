package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;

public interface QuizExerciseService {
    public QuizExercise createQuizExercise(QuizExercise quizExercise);
    public QuizExercise getQuizExerciseById(String exerciseId);
    public QuizQuestion getQuizQuestionByQuestionId(String questionId);
}
