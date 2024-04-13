package com.example.codeE.request.topic;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.material.MaterialResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private boolean isShowAll;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    private List<MaterialResponse> materials;
    private List<ExerciseResponse> exercises;
    private List<GroupTopicResponse> groups;
    public TopicGetResponse(Topic topic, List<MaterialResponse> materials, List<ExerciseResponse> exercises, List<GroupTopicResponse> groups){
        this.topicId = topic.getTopicId();
        this.courseId = topic.getCourseId();
        this.topicName= topic.getTopicName();
        this.description = topic.getDescription();
        this.createdDate= topic.getCreatedDate();
        this.updatedDate = topic.getUpdatedDate();
        this.isShowAll = topic.isShowAll();
        this.materials = materials;
        this.exercises = exercises;
        this.groups = groups;
    }
}
