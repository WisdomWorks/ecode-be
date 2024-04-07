package com.example.codeE.request.exercise.file;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFileSubmissionRequest {
    @NotNull(message = "Student Id is required")
    private String studentId;
    @NotNull(message = "Exercise Id is required")
    private String exerciseId;
    private String url;
}
