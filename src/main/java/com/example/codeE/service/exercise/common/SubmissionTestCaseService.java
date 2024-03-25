package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.SubmissionTestCase;

public interface SubmissionTestCaseService {
    public void deleteAllTcBySubmissionId(String submissionId);
    public SubmissionTestCase getTcBySubmissionId(String submissionId);
    public void deleteTcBySubmissionId(String submissionId);
}
