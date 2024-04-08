package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.*;
import com.example.codeE.model.group.Group;
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
    private List<Group> groups;
    private OverviewScoreReport report;

    public AllSubmissionResponse(Exercise exercise, List<SubmissionDetail> allSubmission, OverviewScoreReport report, List<Group> groups){
        this.allSubmission = allSubmission;
        this.exerciseId = exercise.getExerciseId();
        this.groups = groups;
        this.report = report;
    }
}
