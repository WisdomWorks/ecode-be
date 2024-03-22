package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Exercise;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @ManyToMany
    private List<Language> allowedLanguages;

    public CodeExercise(String exerciseId, String topicId, String exerciseName, String key, String createdDate, String updatedDate, Date startTime, Date endTime, String type, List<String> publicGroupIds, String code, String description, Float timeLimit, Integer memoryLimit, boolean shortCircuit, Float points, boolean partial, List<Language> allowedLanguages) {
        super(exerciseId, topicId, exerciseName, key, createdDate, updatedDate, startTime, endTime, type, publicGroupIds);
        this.code = code;
        this.description = description;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.shortCircuit = shortCircuit;
        this.points = points;
        this.partial = partial;
        this.allowedLanguages = allowedLanguages;
    }
}
