package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.TestCase;
import com.example.codeE.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestcaseImpl implements TestcaseService{
    @Autowired
    TestcaseRepository testcaseRepository;

    @Override
    public TestCase getTestcaseById(String id) {
        return this.testcaseRepository.findById(id).get();
    }

    @Override
    public TestCase createTestcase(TestCase tc) {
        return this.testcaseRepository.save(tc);
    }
}
