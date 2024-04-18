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
public class GetDetailExerciseRequest {
    @NotNull(message = "Exercise id is required")
    private String exerciseId;
    @NotNull(message = "Student id is required")
    private String studentId;
    @NotNull(message = "Exercise key is required")
    private String key;
    @NotNull(message = "User urgent is request is required")
    private String userUrgent;
}
