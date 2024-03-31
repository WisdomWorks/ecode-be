package com.example.codeE.request.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.Exercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseStudentResponse {
    private String exerciseId;
    private String topicId;
    private String exerciseName;
    private String createdDate;
    private String updatedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private String startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private String endTime;
    private int durationTime;
    private String type;

    public ExerciseStudentResponse(Exercise exercise) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_TIME_ISO_FORMAT);
        this.exerciseId = exercise.getExerciseId();
        this.topicId = exercise.getTopicId();
        this.exerciseName = exercise.getExerciseName();
        this.createdDate = exercise.getCreatedDate();
        this.updatedDate = exercise.getUpdatedDate();
        this.startTime = dateFormat.format(exercise.getStartTime());
        this.endTime = dateFormat.format(exercise.getEndTime());
        this.durationTime = exercise.getDurationTime();
        this.type = exercise.getType();
    }
}
