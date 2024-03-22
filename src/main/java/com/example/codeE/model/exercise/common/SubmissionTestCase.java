package com.example.codeE.model.exercise.common;

import com.example.codeE.model.exercise.CodeSubmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionTestCase {
    private String submission;
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
