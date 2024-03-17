package com.example.codeE.request.exercise.essay;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {CreateEssayExerciseRequest.class})
public class CreateEssayExerciseRequest {
    @NotNull(message = "Topic ID is required")
    private String topicId;

    @NotNull(message = "Exercise's name is required")
    private String exerciseName;

    @NotNull(message = "Exercise's key is required")
    private String key;

    @NotNull(message = "Exercise start time is required")
    @Future(message = "Exercise start time must be in the future")
    private Date startTime;

    @NotNull(message = "Exercise end time is required")
    @Future(message = "Exercise end time must be in the future")
    private Date endTime;

    @NotNull(message = "Exercise public option is required")
    private List<String> publicGroupIds;

    @NotNull(message = "Question is required")
    private String question;
}
