package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.problem.TestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "code_exercise")
public class CodeExercise extends Exercise {

    @Field
    private String description;

//    @NotNull(message = "Time limit is required")
    @Field
    private Double timeLimit;

    @Field
//    @NotNull(message = "Memory limit is required")
    private Integer memoryLimit;

    @Field
    private Boolean shortCircuit = true;

    @Field
    private boolean partial = true;

    @Field
    private List<String> allowedLanguageIds;

    @Field
    private Double points;

    @Field
    private String template;

    @Field
    private List<TestCase> testCases;
}
