package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Submission;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CodeSubmission")
public class CodeSubmission extends Submission {
    @Field
    private Float time;
    @Field
    private Float memory;

    @ManyToOne
    private Language language;
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
    private Float casePoints;
    @Field
    private Float caseTotal;

    @ManyToOne
    private Judge judgedOn;

    //    private LocalDateTime judgedDate;
    @Field
    private boolean isPretested;
    @Field
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
