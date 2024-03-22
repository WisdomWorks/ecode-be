package com.example.codeE.model.exercise;

import com.example.codeE.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CodeSubmission")
public class CodeSubmission extends Submission {
    @Field
    private float time;

    @Field
    private float memory;

    @Field
    private String languageId;

    @Field
    private String status;

    @Field
    private String result;

    @Field
    private String error;

    @Field
    private Integer currentTestcase;

    @Field
    private boolean batch;

    @Field
    private float casePoints;

    @Field
    private float caseTotal;

    @Field
    private String judgedOn;

    @Field
    private boolean isPretested;

    @Field
    private LocalDateTime lockedAfter;

    public CodeSubmission(String studentId, String exerciseId, float score, boolean reviewable, float time, float memory, String languageId, String status, String result, String error, Integer currentTestcase, boolean batch, float casePoints, float caseTotal, String judgedOn, boolean isPretested, LocalDateTime lockedAfter) {
        super(studentId, exerciseId, score, reviewable);
        this.time = time;
        this.memory = memory;
        this.languageId = languageId;
        this.status = status;
        this.result = result;
        this.error = error;
        this.currentTestcase = currentTestcase;
        this.batch = batch;
        this.casePoints = casePoints;
        this.caseTotal = caseTotal;
        this.judgedOn = judgedOn;
        this.isPretested = isPretested;
        this.lockedAfter = lockedAfter;
    }

    public String getResultFromCode(String result, float casePoints, float caseTotal) {
        if (result.equals("AC")) {
            if (casePoints == caseTotal) {
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

    public float memoryBytes() {
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

    public void judge(boolean rejudge) {
//        judgeSubmission(this, rejudge);
    }

    public void abort() {
//        abortSubmission(this);
    }

    public boolean isGraded() {
        return !this.status.equals("QU") && !this.status.equals("P") && !this.status.equals("G");
    }
}
