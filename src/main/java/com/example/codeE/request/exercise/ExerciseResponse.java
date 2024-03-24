package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.request.group.GroupTopicResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponse {
    private String exerciseId;
    private String topicId;
    private String exerciseName;
    private String createdDate;
    private String updatedDate;
    private Date startTime;
    private Date endTime;
    private int durationTime;
    private boolean isShowAll;
    private String type;
    private int reAttempt;
    private List<GroupTopicResponse> groups;

    public ExerciseResponse(Exercise exercise, List<GroupTopicResponse> groups) {
        this.exerciseId = exercise.getExerciseId();
        this.topicId = exercise.getTopicId();
        this.exerciseName = exercise.getExerciseName();
        this.createdDate = exercise.getCreatedDate();
        this.updatedDate = exercise.getUpdatedDate();
        this.startTime = exercise.getStartTime();
        this.endTime = exercise.getEndTime();
        this.durationTime = exercise.getDurationTime();
        this.isShowAll = exercise.isShowAll();
        this.type = exercise.getType();
        this.reAttempt = exercise.getReAttempt();
        this.groups = groups;
    }
}
