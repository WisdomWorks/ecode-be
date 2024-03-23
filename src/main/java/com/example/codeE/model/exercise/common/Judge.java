package com.example.codeE.model.exercise.common;

import com.example.codeE.service.judge.JudgeService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.codeE.constant.Constant.SERVER_NAME;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Judge {
    private String judgeId = "sgfdskbg23";

    private String name = SERVER_NAME;

    private LocalDateTime created = LocalDateTime.now();

    private String authKey = "fdsf9h22re";

    private Boolean isBlocked = false;

    private Boolean isDisabled = false;

    private Boolean online = false;

    private LocalDateTime startTime = LocalDateTime.now();

    @Positive(message = "Response time must be positive")
    private Double ping;

    @Positive(message = "System load must be positive")
    private Double load;

    private String description;

    private String lastIp;

//    @ManyToMany
//    private List<CodeExerciseWBD> problems;

    private List<String> problemIds;

    private List<String> runtimeIds;

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
