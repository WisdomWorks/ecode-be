package com.example.codeE.model.exercise.common;

import com.example.codeE.service.judge.JudgeService;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "judge")
public class Judge {
    @Id
    private String judgeId;

    @NotBlank(message = "Judge name is required")
    @Size(max = 50, message = "Judge name must not exceed 50 characters")
    @Indexed(unique = true)
    @Field("name")
    private String name = "DefaultJudge";

    @CreatedDate
    @Field("created")
    private LocalDateTime created;

    @NotBlank(message = "Authentication key is required")
    @Size(max = 100, message = "Authentication key must not exceed 100 characters")
    @Field("auth_key")
    private String authKey;

    @NotNull(message = "Block judge status is required")
    @Field("is_blocked")
    private Boolean isBlocked = false;

    @NotNull(message = "Disable judge status is required")
    @Field("is_disabled")
    private Boolean isDisabled = false;

    @NotNull(message = "Judge online status is required")
    @Field("online")
    private Boolean online = false;

    @Field("start_time")
    private LocalDateTime startTime = LocalDateTime.now();

    @Positive(message = "Response time must be positive")
    @Field("ping")
    private Double ping;

    @Positive(message = "System load must be positive")
    @Field("load")
    private Double load;

    @Field("description")
    private String description;

    @Pattern(regexp = "^(\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    @Field("last_ip")
    private String lastIp;

//    @ManyToMany
//    private List<CodeExerciseWBD> problems;

    @Field("problems")
    private List<String> problemIds;

    @Field("runtimes")
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
