package com.example.codeE.request.exercise.quiz;

import com.example.codeE.model.exercise.common.QuizQuestion;
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
public class UpdateQuizExerciseRequest {
    @NotNull(message = "Exercise ID is required")
    private String exerciseId;
    @NotNull(message = "Topic ID is required")
    private String topicId;
    @NotNull(message = "Exercise's name is required")
    private String exerciseName;
    @NotNull(message = "Exercise's key is required")
    private String key;
    @NotNull(message = "Start time is required")
    private Date startTime;
    @NotNull(message = "End time is required")
    private Date endTime;
    @NotNull(message = "Duration time is required")
    private int durationTime;
    private int reAttempt;
    private String exerciseDescription;
    private List<QuizQuestion> questions;
}
