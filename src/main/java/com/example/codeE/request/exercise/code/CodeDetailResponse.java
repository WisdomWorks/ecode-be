package com.example.codeE.request.exercise.code;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.request.exercise.quiz.QuizQuestionResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CodeDetailResponse {
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
    private List<TestCase> testCases;
    private HashMap<String, String> languageTemplate;

    public CodeDetailResponse(CodeExercise codeExercise) {
        this.exerciseId = codeExercise.getExerciseId();
        this.exerciseName = codeExercise.getExerciseName();
        this.topicId = codeExercise.getTopicId();
        this.startTime = codeExercise.getStartTime();
        this.endTime = codeExercise.getEndTime();
        this.durationTime = codeExercise.getDurationTime();
        this.reAttempt = codeExercise.getReAttempt();
        this.type = codeExercise.getType();
        this.timeLess = GetTimeLess(codeExercise.getStartTime(), codeExercise.getEndTime(), codeExercise.getDurationTime());
        this.exerciseDescription = codeExercise.getExerciseDescription();
        this.testCases = codeExercise.getTestCases();
        this.languageTemplate = getTemplateMap(codeExercise.getAllowedLanguageIds());
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

    private HashMap<String, String> getTemplateMap(List<String> allowedLanguageIds) {
        HashMap<String, String> languageTemplate = new HashMap<>();
        for (String languageId : allowedLanguageIds) {
            // template = null if key not found, search using Constant.LANGUAGE_TEMPLATE.get(languageId)
            String template = Constant.LANGUAGE_TEMPLATE.get(languageId);
            languageTemplate.put(languageId, template);
        }
        return languageTemplate;
    }
}
