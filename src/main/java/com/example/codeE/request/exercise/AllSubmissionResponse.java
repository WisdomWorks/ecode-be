package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.*;
import com.example.codeE.model.user.User;
import com.example.codeE.request.report.OverviewScoreReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllSubmissionResponse {
    private List<SubmissionDetail> allSubmission;
    private String exerciseId;
    private List<String> publicGroupIds;
    private OverviewScoreReport report;

    public AllSubmissionResponse(Exercise exercise, List<SubmissionDetail> allSubmission, OverviewScoreReport report){
        this.allSubmission = allSubmission;
        this.exerciseId = exercise.getExerciseId();
        this.publicGroupIds = exercise.getPublicGroupIds();
        this.report = report;
    }
}
