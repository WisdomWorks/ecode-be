package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;

public interface CodeSubmissionService {
    CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission);
    void updateStatusAndResult(String submissionId, String status, String result);
    void updateStatusAndResultBySubmissionIdAndStatus(String submissionId, String searchedStatus, String status, String result);

}
