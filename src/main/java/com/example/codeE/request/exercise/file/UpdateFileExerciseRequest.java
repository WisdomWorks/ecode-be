package com.example.codeE.request.exercise.file;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFileExerciseRequest {
    @NotNull(message = "Exercise ID is required")
    private String exerciseId;
    @NotNull(message = "Topic ID is required")
    private String topicId;
    @NotNull(message = "Exercise's name is required")
    private String exerciseName;
    private String key;
    @NotNull(message = "Start time is required")
    private Date startTime;
    @NotNull(message = "End time is required")
    private Date endTime;
    @NotNull(message = "Duration time is required")
    private int durationTime;
    private int reAttempt;
    private String exerciseDescription;
    @NotNull(message = "Question is required")
    private String question;
}
