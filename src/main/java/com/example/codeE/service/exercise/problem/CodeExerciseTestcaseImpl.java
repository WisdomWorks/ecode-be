package com.example.codeE.service.exercise.problem;

import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.repository.CodeExerciseRepository;
import com.example.codeE.repository.CodeExerciseTestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeExerciseTestcaseImpl implements CodeExerciseTestcaseService{
    @Autowired
    private CodeExerciseTestcaseRepository codeExerciseTestcaseRepository;

    @Autowired
    private CodeExerciseRepository codeExerciseRepository;

    @Override
    public TestCase saveTestCase(TestCase testCase) {
        return this.codeExerciseTestcaseRepository.save(testCase);
    }

    @Override
    public void deleteTestCase(String id) {
        this.codeExerciseTestcaseRepository.deleteById(id);
    }

    @Override
    public List<TestCase> getAllZeroPointTestCases(String exerciseId) {
        return this.codeExerciseTestcaseRepository.findByExerciseIdAndPoints(exerciseId, 0);
    }
}
