package com.example.codeE.request.exercise;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {DeleteExerciseRequest.class})
public class DeleteExerciseRequest {
    @NotBlank(message = "Exercise ID is required")
    private String exerciseId;

    @NotBlank(message = "Exercise type is required")
    @Pattern(regexp = "^(quizz|essay|code)$", message = "Exercise type should be quizz, essay, or code")
    private String type;
}
