package com.example.codeE.model.exercise.common;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import com.example.codeE.service.judge.JudgeService;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    //??
    @ManyToMany
    private List<CodeExerciseWBD> problems;

    //??
    @ManyToMany
    private List<Language> runtimes;

    @Autowired
    private JudgeService judgeService;

    public void disconnect(boolean force) {
        judgeService.disconnectJudge(this, force);
    }

    public void toggleDisabled() {
        isDisabled = !isDisabled;
        judgeService.updateDisableJudge(this);
    }
}
