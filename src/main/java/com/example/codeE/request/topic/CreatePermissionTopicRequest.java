package com.example.codeE.request.topic;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionTopicRequest {
    @NotNull(message = "Topic ID is required")
    private String topicId;
    private List<String> groupIds;
    @NotNull(message = "Show all status is required")
    boolean isShowAll = true;
}
