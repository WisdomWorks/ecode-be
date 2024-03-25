package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;

public interface CodeSubmissionService {
    public void updateStatusAndResultBySubmissionIdAndStatus(String submissionId, String searchedStatus, String status, String result);
    public CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission);
    public void updateStatusAndResult(String submissionId, String status, String result);
    public CodeSubmission getCodeSubmissionById(String id);
    public CodeSubmission updateCodeSubmission(CodeSubmission codeSubmission);
}
