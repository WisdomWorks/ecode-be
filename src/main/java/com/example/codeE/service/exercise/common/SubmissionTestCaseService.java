package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.SubmissionTestCase;

import java.util.List;

public interface SubmissionTestCaseService {
    void deleteAllTcBySubmissionId(String submissionId);
    int getMaxPosition(List<SubmissionTestCase> testCases);
    void saveAll(List<SubmissionTestCase> testCases);
    List<SubmissionTestCase> findBySubmissionId(String submissionId);
}
