package com.example.codeE.request.exercise.file.response;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.FileExercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDetailResponse {
    private String exerciseId;
    private String topicId;
    private String exerciseName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date endTime;
    private int durationTime;
    private int reAttempt;
    private String type;
    private String exerciseDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.TIME_FORMAT)
    private Date timeLess;
    private String question;

    public FileDetailResponse(FileExercise fileExercise){
        this.exerciseId = fileExercise.getExerciseId();
        this.exerciseName = fileExercise.getExerciseName();
        this.topicId = fileExercise.getTopicId();
        this.startTime = fileExercise.getStartTime();
        this.endTime = fileExercise.getEndTime();
        this.durationTime = fileExercise.getDurationTime();
        this.reAttempt = fileExercise.getReAttempt();
        this.type = fileExercise.getType();
        this.exerciseDescription = fileExercise.getExerciseDescription();
        this.timeLess = GetTimeLess(fileExercise.getStartTime(), fileExercise.getEndTime(), fileExercise.getDurationTime());
        this.question = fileExercise.getQuestion();
    }
    private Date GetTimeLess(Date startTime, Date endTime, int durationTime) {
        if(startTime == endTime){
            return new Date(0);
        }
        var current = new Date().getTime();
        if(current > endTime.getTime()){
            return new Date(0);
        }
        if (current + ((long) durationTime * 60 * 1000) < endTime.getTime()) {
            return new Date((long) durationTime * 60 * 1000);
        } else {
            return new Date(endTime.getTime() - current);
        }
    }
}
