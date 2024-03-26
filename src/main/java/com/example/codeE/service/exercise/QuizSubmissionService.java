package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizQuestion;

import java.util.List;

public interface QuizSubmissionService {
    public QuizSubmission createSubmission(QuizSubmission quizSubmission);

    public float gradeSubmission(List<QuizAnswers> quizSubmission,List<QuizQuestion>  quizExercise);
}
