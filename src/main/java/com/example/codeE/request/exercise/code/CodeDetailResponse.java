package com.example.codeE.request.exercise.code;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String description;
    private List<TestCase> testCases;
    private HashMap<String, String> languageTemplate;
    private boolean isUsingAiGrading = false;

    public CodeDetailResponse(CodeExercise codeExercise, Date timeDoExercise) {
        this.exerciseId = codeExercise.getExerciseId();
        this.exerciseName = codeExercise.getExerciseName();
        this.topicId = codeExercise.getTopicId();
        this.startTime = codeExercise.getStartTime();
        this.endTime = codeExercise.getEndTime();
        this.durationTime = codeExercise.getDurationTime();
        this.reAttempt = codeExercise.getReAttempt();
        this.type = codeExercise.getType();
        this.timeLess = GetTimeLess(codeExercise.getStartTime(), codeExercise.getEndTime(), codeExercise.getDurationTime(),timeDoExercise);
        this.description = codeExercise.getDescription();
        this.testCases = codeExercise.getTestCases();
        this.languageTemplate = getTemplateMap(codeExercise.getAllowedLanguageIds());
        this.isUsingAiGrading = codeExercise.isUsingAiGrading();
    }

    private Date GetTimeLess(Date startTime, Date endTime, int durationTime,Date timeDoExercise) {
        if(startTime == endTime){
            return new Date(0);
        }
        var currentStart = timeDoExercise.getTime();
        var timeNow = new Date().getTime();
        if(timeNow > endTime.getTime()){
            return new Date(0);
        }
        if (currentStart + ((long) durationTime * 60 * 1000) < endTime.getTime()) {
            var a = currentStart + ((long) durationTime * 60 * 1000);
            return new Date((long) a - timeNow);
        } else {
            return new Date((long) endTime.getTime() - timeNow);
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
