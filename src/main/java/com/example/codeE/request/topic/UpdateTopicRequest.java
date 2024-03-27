package com.example.codeE.request.topic;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTopicRequest {
    @NotBlank(message = "Topic ID is required")
    private String topicId;
    @NotBlank(message = "Topic Name is required")
    private String topicName;
    private String description;
    @NotBlank(message = "Show all status is required")
    private boolean isShowAll;
}
