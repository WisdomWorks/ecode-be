package com.example.codeE.model.judge;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SubmissionTestCase {
    private CodeSubmission submission;
    private int testCaseId;
    private String status;
    private Float time;
    private Float memory;
    private Float points;
    private Float total;
    private Integer batch;
    private String feedback;
    private String extendedFeedback;
    private String output;
}
