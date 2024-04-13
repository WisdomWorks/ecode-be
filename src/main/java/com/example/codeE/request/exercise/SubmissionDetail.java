package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.*;
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
public class SubmissionDetail {
    private String userId;
    private String userName;
    private String name;
    private Submission submission;
    private List<GroupTopicResponse> groups;

    public SubmissionDetail(User user, Submission submission, List<GroupTopicResponse> groups) {
        this.userId = user.getName();
        this.userName = user.getUsername();
        this.name = user.getName();
        this.submission = submission;
        this.groups = groups;
    }
}
