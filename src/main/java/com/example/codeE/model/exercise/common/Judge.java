package com.example.codeE.model.exercise.common;

import com.example.codeE.model.exercise.CodeExercise;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Judge {
    @NotBlank(message = "Name is required")
    private String name = "DefaultJudge";

    private LocalDateTime created = LocalDateTime.now();

    @NotBlank(message = "Auth key is required")
    private String authKey;

    private boolean isBlocked = false;

    private boolean isDisabled = false;

    private boolean online = true;

    private LocalDateTime startTime = LocalDateTime.now();

    private Float ping = null;

    private Float load = null;

    private String description = "Default judge for the online judge system";

    private String lastIp;

    @ManyToMany
    private List<CodeExercise> problems;

    @ManyToMany
    private List<Language> runtimes;

    // Constructors, getters, and setters
}
