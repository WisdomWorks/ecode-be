package com.example.codeE.model.exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
//    @NotBlank(message = "Problem code is required")
//    @Size(max = 20, message = "Problem code must not exceed 20 characters")
//    @Indexed(unique = true)
//    @Field("code")
//    private String code;

    @NotBlank(message = "Problem name is required")
    @Size(max = 100, message = "Problem name must not exceed 100 characters")
    @Field("name")
    private String name;

    @Field
    private String description;

//    @NotNull(message = "Time limit is required")
    @Field("time_limit")
    private Float timeLimit;

    @Field("memory_limit")
//    @NotNull(message = "Memory limit is required")
    private Integer memoryLimit;

    @Field("short_circuit")
    private Boolean shortCircuit;

    @Field
    private boolean partial = true;

    @Field("allowed_language_ids")
    private List<String> allowedLanguageIds;
}
