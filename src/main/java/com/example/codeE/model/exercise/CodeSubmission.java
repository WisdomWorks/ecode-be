package com.example.codeE.model.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.service.judge.JudgeService;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "code_submission")
public class CodeSubmission extends Submission {
    @Field
    private Double time;

    @Field
    private Integer memory;

    @Field
    private String languageId;

    @Field
    private String status = "QU";

    @Field
    private String result;

    @Field
    private String error;

    @Field
    private Integer currentTestcase;

    @Field
    private Double casePoints;

    @Field
    private Double caseTotal;

    private String judgedOn = "ExampleJudge";

    @Field
    private boolean isPretested;

    @Field
    private LocalDateTime lockedAfter;

    @Field
    private String source;

    @Transient
    private JudgeService judgeService;

    public CodeSubmission(String submissionId, @NotNull(message = "Student ID is required") String studentId, @NotNull(message = "Exercise ID is required") String exerciseId, Float score, String dateSubmit, String dateGrade, @NotNull(message = "Reviewable is required") boolean reviewable, Double time, Integer memory, String languageId, String status, String result, String error, Integer currentTestcase, Double casePoints, Double caseTotal, String judgedOn, boolean isPretested, LocalDateTime lockedAfter, String source) {
        super(submissionId, studentId, exerciseId, score, dateSubmit, dateGrade,"",reviewable);
        this.time = time;
        this.memory = memory;
        this.languageId = languageId;
        this.status = status;
        this.result = result;
        this.error = error;
        this.currentTestcase = currentTestcase;
        this.casePoints = casePoints;
        this.caseTotal = caseTotal;
        this.judgedOn = judgedOn;
        this.isPretested = isPretested;
        this.lockedAfter = lockedAfter;
        this.source = source;
    }

    public CodeSubmission(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    public CodeSubmission(String studentId, String exerciseId, Float score, boolean reviewable, Double time, Integer memory, String languageId, String status, String result, String error, Integer currentTestcase, Double casePoints, Double caseTotal, String judgedOn, boolean isPretested, LocalDateTime lockedAfter, String source) {
        super(studentId, exerciseId, score, reviewable, "");
        this.time = time;
        this.memory = memory;
        this.languageId = languageId;
        this.status = status;
        this.result = result;
        this.error = error;
        this.currentTestcase = currentTestcase;
        this.casePoints = casePoints;
        this.caseTotal = caseTotal;
        this.judgedOn = judgedOn;
        this.isPretested = isPretested;
        this.lockedAfter = lockedAfter;
        this.source = source;
    }

    public CodeSubmission(String submissionId, String exerciseId, String languageId, String judgedOn, String source) {
        super(submissionId, exerciseId);
        this.languageId = languageId;
        this.judgedOn = judgedOn;
        this.source = source;
    }

    public static String getResultFromCode(String result, Double casePoints, Double caseTotal) {
        if (result.equals("AC")) {
//            Number objects are compared using '==', not 'equals()'
            if (casePoints.equals(caseTotal)) {
                return "AC";
            }
            return "_AC";
        }
        return result;
    }

    public String getResult() {
        if (this.status.equals("IE") || this.status.equals("CE")) {
            return this.status;
        }
        return getResultFromCode(this.result, this.casePoints, this.caseTotal);
    }

    public Integer memoryBytes() {
        return this.memory * 1024;
    }

    public String getShortStatus() {
        return this.result != null ? this.result : this.status;
    }

    public String getLongStatus() {
        return Constant.USER_DISPLAY_CODES.get(this.getShortStatus());
    }

    public boolean isLocked() {
        return this.lockedAfter != null && this.lockedAfter.isBefore(LocalDateTime.now());
    }

    public void judge(boolean rejudge, boolean forceJudge) {
        if (forceJudge || !this.isLocked()) {
            judgeService.judgeSubmission(this, rejudge);
        }
    }

    public void abort() {
        judgeService.abortSubmission(this);
    }

    public boolean isGraded() {
        return !this.status.equals("QU") && !this.status.equals("P") && !this.status.equals("G");
    }
}
