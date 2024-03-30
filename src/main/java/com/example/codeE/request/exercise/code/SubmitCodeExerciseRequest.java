package com.example.codeE.request.exercise.code;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitCodeExerciseRequest {
    @NotNull(message = "Student ID is required")
    private String studentId;

    @NotNull(message = "Exercise ID field is required")
    private String exerciseId;

    @NotNull(message = "Language ID field is required")
    private String languageId;

    @NotNull(message = "Source field is required")
    private String source;
}
