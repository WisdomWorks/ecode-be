package com.example.codeE.request.exercise.essay;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.EssayExercise;
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
public class EssayDetailResponse {
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

    public EssayDetailResponse(EssayExercise essayExercise){
        this.exerciseId = essayExercise.getExerciseId();
        this.exerciseName = essayExercise.getExerciseName();
        this.topicId = essayExercise.getTopicId();
        this.startTime = essayExercise.getStartTime();
        this.endTime = essayExercise.getEndTime();
        this.durationTime = essayExercise.getDurationTime();
        this.reAttempt = essayExercise.getReAttempt();
        this.type = essayExercise.getType();
        this.exerciseDescription = essayExercise.getExerciseDescription();
        this.timeLess = GetTimeLess(essayExercise.getStartTime(), essayExercise.getEndTime(), essayExercise.getDurationTime());
        this.question = essayExercise.getQuestion();
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
            return new Date((long) endTime.getTime() - current);
        }
    }
}
