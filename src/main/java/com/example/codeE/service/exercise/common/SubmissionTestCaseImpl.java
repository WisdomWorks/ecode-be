package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.repository.SubmissionTestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionTestCaseImpl implements SubmissionTestCaseService{
    @Autowired
    private SubmissionTestCaseRepository submissionTestCaseRepository;

    @Override
    public void deleteAllTcBySubmissionId(String submissionId) {
        List<SubmissionTestCase> testCases = submissionTestCaseRepository.findBySubmissionId(submissionId);
        submissionTestCaseRepository.deleteAll(testCases);
    }

    @Override
    public int getMaxPosition(List<SubmissionTestCase> testCases) {
        List<Integer> positions = new ArrayList<>();
        for (SubmissionTestCase testCase : testCases) {
            positions.add(testCase.getTestCaseId());
        }
        return positions.stream().max(Integer::compare).get();
    }

    @Override
    public void saveAll(List<SubmissionTestCase> testCases) {
        submissionTestCaseRepository.saveAll(testCases);
    }

    @Override
    public List<SubmissionTestCase> findBySubmissionId(String submissionId) {
        return submissionTestCaseRepository.findBySubmissionId(submissionId);
    }

    @Override
    public List<SubmissionTestCase> getAllTcBySubmissionId(String submissionId) {
        List<SubmissionTestCase> submissionTestCases = submissionTestCaseRepository.findBySubmissionId(submissionId);
        // Get only the cases with total point = 0
        List<SubmissionTestCase> testCases = new ArrayList<>();
        for (SubmissionTestCase submissionTestCase : submissionTestCases) {
            if (submissionTestCase.getTotal() == 0) {
                testCases.add(submissionTestCase);
            }
        }
        return testCases;
    }
}
