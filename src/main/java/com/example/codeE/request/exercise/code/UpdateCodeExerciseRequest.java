package com.example.codeE.request.exercise.code;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date endTime;

    private int durationTime;

    private boolean isShowAll;

    private int reAttempt;

    private String exerciseDescription;

    @Pattern(regexp = "^(quiz|essay|code)$", message = "Exercise type should be quiz, essay, or code")
    private String type;

    private List<String> publicGroupIds;

    private String description;

    private Double timeLimit;

    private Integer memoryLimit;

    private List<String> allowedLanguageIds;

    private Double points;

    private String template;

    private List<TestCase> testCases;
}
