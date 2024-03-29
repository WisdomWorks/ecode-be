package com.example.codeE.request.exercise;

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
public class CreatePermissionExerciseRequest {
    @NotNull(message = "Exercise id is required")
    private String ExerciseId;
    private List<String> groupIds;
    @NotNull(message = "Show all status is required")
    boolean isShowAll = true;
}
