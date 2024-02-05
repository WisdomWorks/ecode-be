package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.TestCase;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

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
    private TestCase testcase;

    public CodeExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, String type, Boolean isPublic,
                        String language, String functionName, String template, String description, TestCase testcase) {
        super(topicId, exerciseName, key, startTime, endTime, type, isPublic);
        this.language = language;
        this.functionName = functionName;
        this.template = template;
        this.description = description;
        this.testcase = testcase;
    }
}
