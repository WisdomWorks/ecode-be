package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class CodeExercise extends Exercise {
    @NotBlank(message = "Code is required")
    private String code;
    private String description;
    @NotNull(message = "Time limit is required")
    private Float timeLimit;
    @NotNull(message = "Memory limit is required")
    private Integer memoryLimit;
    private boolean shortCircuit = false;
    @NotNull(message = "Points is required")
    private Float points;
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
