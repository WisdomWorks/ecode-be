package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;

public interface CodeSubmissionService {
    void updateStatusAndResultBySubmissionIdAndStatus(String submissionId, String searchedStatus, String status, String result);
    CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission);
    void updateStatusAndResult(String submissionId, String status, String result);
    CodeSubmission getCodeSubmissionById(String id);
    CodeSubmission updateCodeSubmission(CodeSubmission codeSubmission);
    CodeSubmission findByBySubmissionIdAndStatus(String submissionId, String searchedStatus);
}
