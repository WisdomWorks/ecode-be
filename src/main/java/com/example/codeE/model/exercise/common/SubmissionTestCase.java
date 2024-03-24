package com.example.codeE.model.exercise.common;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "submission_testcase")
public class SubmissionTestCase {
    @Id
    private String submissionTestCaseId;

    @NotNull(message = "Submission ID is required")
    @Field("submission_id")
    private String submissionId;

    @NotNull(message = "Test case ID is required")
    @Field("case_id")
    private Integer testCaseId;

    @NotBlank(message = "Status is required")
    @Field("status")
    private String status;

    @Field("time")
    private Double time;

    @Field("memory")
    private Double memory;

    @Field("points")
    private Double points;

    @Field("total")
    private Double total;

    @Field("feedback")
    private String feedback;

    @Field("extended_feedback")
    private String extendedFeedback;

    @Field("output")
    private String output;

}
