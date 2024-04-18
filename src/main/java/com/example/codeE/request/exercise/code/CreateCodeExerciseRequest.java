package com.example.codeE.request.exercise.code;

import com.example.codeE.model.exercise.common.problem.TestCase;
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
public class CreateCodeExerciseRequest {
    @NotNull(message = "Topic ID is required")
    private String topicId;

    @NotNull(message = "Exercise's name is required")
    private String exerciseName;

    @NotNull(message = "Exercise's key is required")
    private String key;

    @NotNull(message = "Exercise start time is required")
//    @Future(message = "Exercise start time must be in the future")
    private Date startTime;

    @NotNull(message = "Exercise end time is required")
    @Future(message = "Exercise end time must be in the future")
    private Date endTime;

    @NotNull(message = "Duration time is required")
    private int durationTime;

    @NotNull(message = "Re-Attempt is required")
    private int reAttempt;

    @NotNull(message = "Exercise description is required")
    private String description;

//    @NotNull(message = "Time limit is required")
//    private Double timeLimit;
//
//    @NotNull(message = "Memory limit is required")
//    private Integer memoryLimit;

    @NotNull(message = "Allowed language Ids is required")
    private List<String> allowedLanguageIds;

    @NotNull(message = "Exercise points is required")
    private Double points;

    @NotNull(message = "Exercise testcase is required")
    private List<TestCase> testCases;

    private String template;

    private boolean isUsingAiGrading = false;
}
