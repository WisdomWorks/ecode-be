package com.example.codeE.model.exercise;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CodeExercise")
public class CodeExercise extends Exercise{
    @Field
    @NotNull(message = "Programming language is required")
    @Pattern(regexp = "^(c|c++|java|python)$", message = "Exercise type should be c, c++, java or python")
    private String language;

    @Field
    @NotNull(message = "Function name is required")
    private String functionName;

    @Field
    @NotNull(message = "Template exercise is required")
    private String template;

    @Field
    @NotNull(message = "Exercise description is required")
    private String description;

    @Field
    @NotNull(message = "Testcase is required")
    private List<String> testcases;

    public CodeExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, String type, List<String> publicGroupIds,
                        String language, String functionName, String template, String description, List<String> testcases) {
        super(topicId, exerciseName, key, startTime, endTime, type, publicGroupIds);
        this.language = language;
        this.functionName = functionName;
        this.template = template;
        this.description = description;
        this.testcases = testcases;
    }
}
