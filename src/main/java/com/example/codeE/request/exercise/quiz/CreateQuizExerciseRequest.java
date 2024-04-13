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
public class CreateQuizExerciseRequest {
    @NotNull(message = "Topic ID is required")
    private String topicId;
    @NotNull(message = "Exercise's name is required")
    private String exerciseName;
    @NotNull(message = "Exercise's key is required")
    private String key;
    @NotNull(message = "Start Time is required")
    private Date startTime;
    @NotNull(message = "End Time is required")
    private Date endTime;
    @NotNull(message = "duration Time is required")
    private int durationTime;
    private int reAttempt;
    private String exerciseDescription;
    @NotNull(message = "Exercise's questions is required")
    private List<QuizQuestion> questions;
}
