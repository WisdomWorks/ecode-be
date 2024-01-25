package com.example.codeE.model.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @NonNull
    private String topicId;
    @NonNull
    private String courseId;
    @NonNull
    private String topicName;
    private String description;
    @NonNull
    private String createBy;
    @NonNull
    private Boolean isPublic;
}
