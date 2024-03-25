package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.SubmissionTestCase;

import java.util.List;

public interface SubmissionTestCaseService {
    public void deleteAllTcBySubmissionId(String submissionId);
    public int getMaxPosition(List<SubmissionTestCase> testCases);
    public void saveAll(List<SubmissionTestCase> testCases);
}
