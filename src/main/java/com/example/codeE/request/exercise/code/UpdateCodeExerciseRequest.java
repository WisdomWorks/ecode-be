package com.example.codeE.request.exercise.code;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class UpdateCodeExerciseRequest {
    @NotNull(message = "Exercise ID is required")
    private String exerciseId;

    private String topicId;

    private String exerciseName;

    private String key;

    private Date startTime;

    @Future(message = "Exercise end time must be in the future")
    private Date endTime;

    private int durationTime;

    private int reAttempt;

    private String exerciseDescription;

    @Pattern(regexp = "^(quiz|essay|code)$", message = "Exercise type should be quiz, essay, or code")
    private String type;

    private String description;

    private Double timeLimit;

    private Integer memoryLimit;

    private List<String> allowedLanguageIds;

    private Double points;

    private String template;

    private List<TestCase> testCases;
}
