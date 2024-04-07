package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.*;
import com.example.codeE.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDetail {
    private String userId;
    private String userName;
    private String name;
    private Submission submission;

    public SubmissionDetail(User user, Submission submission) {
        this.userId = user.getName();
        this.userName = user.getUsername();
        this.name = user.getName();
        this.submission = submission;
    }
}
