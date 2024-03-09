package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.QuizSubmission;

public interface QuizSubmissionService {
    public QuizSubmission createSubmission(QuizSubmission quizSubmission);

    public float gradeSubmission(QuizSubmission quizSubmission, QuizExercise quizExercise);
}
