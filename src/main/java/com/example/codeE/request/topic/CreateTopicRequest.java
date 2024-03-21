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
public class CreateTopicRequest {
    @NotBlank(message = "Course id is required")
    private String courseId;
    @NotBlank(message = "Topic Name id is required")
    private String topicName;
    private String description = "";
}
