package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Exercise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Document(collection = "CodeExercise")
public class CodeExercise extends Exercise {
    @Field
    @NotBlank(message = "Code is required")
    private String code;
    @Field
    private String description;
    @Field
    @NotNull(message = "Time limit is required")
    private Float timeLimit;
    @Field
    @NotNull(message = "Memory limit is required")
    private Integer memoryLimit;
    @Field
    private boolean shortCircuit = false;
    @Field
    @NotNull(message = "Points is required")
    private Float points;
    @Field
    private boolean partial;
    //    @ManyToMany
//    private List<Language> allowedLanguages;
    private List<String> allowedLanguageIds;
}
