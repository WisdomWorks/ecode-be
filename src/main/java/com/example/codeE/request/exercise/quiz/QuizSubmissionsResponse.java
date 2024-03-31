package com.example.codeE.request.exercise.quiz;

import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizSubmissionsResponse {
    private QuizSubmission submissions;
    private User student;
}
