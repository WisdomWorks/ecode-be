package com.example.codeE.request.exercise.quiz;

import com.example.codeE.model.exercise.common.QuizAnswers;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuizSubmissionRequest {
    @NotNull(message = "Student Id is required")
    private String studentId;
    @NotNull(message = "Exercise Id is required")
    private String exerciseId;
    private List<QuizAnswers> submission;
}
