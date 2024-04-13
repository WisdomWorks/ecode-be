package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.Submission;
import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.model.user.User;
import com.example.codeE.request.group.GroupTopicResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeSubmissionDetail extends SubmissionDetail {
    private List<SubmissionTestCase> testCases;

    public CodeSubmissionDetail(User user, Submission submission, List<GroupTopicResponse> groups, List<SubmissionTestCase> testCases) {
        super(user, submission, groups);
        this.testCases = testCases;
    }
}
