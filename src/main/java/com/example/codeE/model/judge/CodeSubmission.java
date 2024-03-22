package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Submission;
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
    //    @ManyToOne
//    @DBRef
    private Language language;
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
    private Float casePoints;
    @Field
    private Float caseTotal;
//    @ManyToOne
//    @DBRef
//    private Judge judgedOn;

    private String judgedOnId;
    //    private LocalDateTime judgedDate;
    @Field
    private boolean isPretested;
    @Field
    private LocalDateTime lockedAfter;
}
