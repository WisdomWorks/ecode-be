package com.example.codeE.service.exercise.problem;

import com.example.codeE.model.exercise.common.problem.TestCase;

import java.util.List;

public interface CodeExerciseTestcaseService {
    TestCase saveTestCase(TestCase testCase);
    void deleteTestCase(String id);

    List<TestCase> getAllZeroPointTestCases(String exerciseId);
}
