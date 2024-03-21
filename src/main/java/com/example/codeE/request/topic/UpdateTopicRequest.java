package com.example.codeE.request.topic;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {UpdateTopicRequest.class})
public class UpdateTopicRequest {
    @NotBlank(message = "Topic ID is required")
    private String topicId;
    @NotBlank(message = "Topic Name is required")
    private String topicName;
    private String description;
}
