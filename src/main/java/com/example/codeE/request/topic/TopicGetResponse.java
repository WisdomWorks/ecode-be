package com.example.codeE.request.topic;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicGetResponse {

    private String topicId;
    private String courseId;
    private String topicName;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    private List<Material> materials;
    private List<Exercise> exercises;

    public TopicGetResponse(Topic topic, List<Material> materials, List<Exercise> exercises){
        this.topicId = topic.getTopicId();
        this.courseId = topic.getCourseId();
        this.topicName= topic.getTopicName();
        this.description = topic.getDescription();
        this.createdDate= topic.getCreatedDate();
        this.updatedDate = topic.getUpdatedDate();
        this.materials = materials;
        this.exercises = exercises;
    }
}
