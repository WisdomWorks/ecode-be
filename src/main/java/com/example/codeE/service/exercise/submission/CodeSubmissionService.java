package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;

public interface CodeSubmissionService {
    public CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission);
    public void updateStatusAndResult(String submissionId, String status, String result);
}
