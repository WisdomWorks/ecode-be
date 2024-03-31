package com.example.codeE.request.exercise.quiz;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDetailResponse {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.TIME_FORMAT)
    private Date timeLess;
    private String exerciseDescription;
    private List<QuizQuestionResponse> questions;

    public QuizDetailResponse(QuizExercise quizExercise) {
        this.exerciseId = quizExercise.getExerciseId();
        this.exerciseName = quizExercise.getExerciseName();
        this.topicId = quizExercise.getTopicId();
        this.startTime = quizExercise.getStartTime();
        this.endTime = quizExercise.getEndTime();
        this.durationTime = quizExercise.getDurationTime();
        this.reAttempt = quizExercise.getReAttempt();
        this.type = quizExercise.getType();
        this.timeLess = GetTimeLess(quizExercise.getStartTime(), quizExercise.getEndTime(), quizExercise.getDurationTime());
        this.exerciseDescription = quizExercise.getExerciseDescription();
        this.questions = convertQuestion(quizExercise.getQuestions());
    }

    private Date GetTimeLess(Date startTime, Date endTime, int durationTime) {
        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();
        ZonedDateTime utcStartDateTime = ZonedDateTime.ofInstant(startInstant, ZoneId.of("UTC"));
        ZonedDateTime utcEndDateTime = ZonedDateTime.ofInstant(endInstant, ZoneId.of("UTC"));
        startTime = Date.from(utcStartDateTime.toInstant());
        endTime = Date.from(utcEndDateTime.toInstant());
        if(startTime == endTime){
            return new Date(0);
        }
        var current = new Date().getTime();
        if(current > endTime.getTime()){
            return new Date(0);
        }
        if (startTime.getTime() + ((long) durationTime * 60 * 10000) < endTime.getTime()) {
            return new Date((long) durationTime * 60 * 1000);
        } else {
            return new Date((long) endTime.getTime() - current);
        }
    }
    private List<QuizQuestionResponse> convertQuestion(List<QuizQuestion> questions){
        List<QuizQuestionResponse> result = new ArrayList<>();
        for(var i : questions){
            result.add(new QuizQuestionResponse(i));
        }
        return result;
    }
}

