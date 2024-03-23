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
@Document(collection = "code_submission")
public class CodeSubmission extends Submission {
    @Field
    private Float time;

    @Field
    private Float memory;

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
    private Float casePoints;

    @Field
    private Float caseTotal;

    @Field
    private String judgedOn;

    @Field
    private boolean isPretested;

    @Field
    private LocalDateTime lockedAfter;

    public CodeSubmission(String studentId, String exerciseId, Float score, boolean reviewable, Float time, Float memory, String languageId, String status, String result, String error, Integer currentTestcase, boolean batch, Float casePoints, Float caseTotal, String judgedOn, boolean isPretested, LocalDateTime lockedAfter) {
        super(studentId, exerciseId, score, reviewable);
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
    }

    public String getResultFromCode(String result, Float casePoints, Float caseTotal) {
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

    public Float memoryBytes() {
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
