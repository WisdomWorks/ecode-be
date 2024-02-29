package com.example.codeE.request.exercise.code;

import com.example.codeE.model.exercise.common.TestCase;
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
public class CreateCodeExerciseRequest {
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

    @NotNull(message = "Exercise type is required")
    @Pattern(regexp = "^(quiz|essay|code)$", message = "Exercise type should be quiz, essay, or code")
    private String type;

    @NotNull(message = "Exercise public option is required")
    private List<String> publicGroupIds;

    @NotNull(message = "Programming language is required")
    @Pattern(regexp = "^(c|c++|java|python)$", message = "Exercise type should be c, c++, java or python")
    private String language;

    @NotNull(message = "Function name is required")
    private String functionName;

    @NotNull(message = "Template exercise is required")
    private String template;

    @NotNull(message = "Exercise description is required")
    private String description;

    @NotNull(message = "Testcase is required")
    private List<TestCase> testcases;
}
