package com.example.codeE.request.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.user.StudentSubmissionInformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date endTime;
    private int durationTime;
    private boolean isShowAll;
    private String type;
    private int reAttempt;
    private List<GroupTopicResponse> groups;
    private List<StudentSubmissionInformation> students;

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
        this.students = new ArrayList<>();
    }
}
