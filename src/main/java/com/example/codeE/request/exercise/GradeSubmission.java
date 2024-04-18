package com.example.codeE.request.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeSubmission {
    @NotNull(message = "Submission ID is required")
    private String submissionId;
    @NotNull(message = "Score is required")
    private float score;
    private String comment = "";
}
