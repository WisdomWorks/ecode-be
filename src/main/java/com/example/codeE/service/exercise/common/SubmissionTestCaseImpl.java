package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.repository.SubmissionTestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionTestCaseImpl implements SubmissionTestCaseService{
    @Autowired
    private SubmissionTestCaseRepository submissionTestCaseRepository;

    @Override
    public void deleteAllTcBySubmissionId(String submissionId) {
        SubmissionTestCase testCase = submissionTestCaseRepository.findById(submissionId).get();
        submissionTestCaseRepository.delete(testCase);
    }

    @Override
    public SubmissionTestCase getTcBySubmissionId(String submissionId) {
//        return submissionTestCaseRepository.findBy();
        return null;
    }

    @Override
    public void deleteTcBySubmissionId(String submissionId) {
//        List<SubmissionTestCase> testCases = submissionTestCaseRepository.findBySubmissionId(submissionId);
//        submissionTestCaseRepository.deleteAll(testCases);
    }
}
