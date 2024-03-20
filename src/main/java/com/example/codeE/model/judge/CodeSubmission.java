package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Submission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class CodeSubmission extends Submission {
    private Float time;

    private Float memory;

    @ManyToOne
    private Language language;

    private String status;

    private String result;

    private String error;

    private Integer currentTestcase;

    private boolean batch;

    private Float casePoints;

    private Float caseTotal;

    @ManyToOne
    private Judge judgedOn;

//    private LocalDateTime judgedDate;

//    private LocalDateTime rejudgedDate;

    private boolean isPretested;

//    @ManyToOne
//    private Contest contestObject;

    private LocalDateTime lockedAfter;

    public CodeSubmission(String submissionId, @NotNull(message = "Student ID is required") String studentId, @NotNull(message = "Exercise ID is required") String exerciseId, float score, String dateSubmit, String dateGrade, @NotNull(message = "Reviewable is required") boolean reviewable, Float time, Float memory, Language language, String status, String result, String error, Integer currentTestcase, boolean batch, Float casePoints, Float caseTotal, Judge judgedOn, boolean isPretested, LocalDateTime lockedAfter) {
        super(submissionId, studentId, exerciseId, score, dateSubmit, dateGrade, reviewable);
        this.time = time;
        this.memory = memory;
        this.language = language;
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
}
