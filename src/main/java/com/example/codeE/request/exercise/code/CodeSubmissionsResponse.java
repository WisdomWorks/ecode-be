package com.example.codeE.request.exercise.code;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CodeSubmissionsResponse {
    private CodeSubmission submissions;
    private User student;
    private Exercise exercise;
    private List<SubmissionTestCase> resultTestcases;
}
