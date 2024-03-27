package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.TestCase;

public interface TestcaseService {
    TestCase getTestcaseById(String id);

    TestCase createTestcase(TestCase tc);
}
