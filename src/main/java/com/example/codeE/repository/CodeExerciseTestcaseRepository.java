package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.problem.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CodeExerciseTestcaseRepository extends MongoRepository<TestCase, String> {
    List<TestCase> findByExerciseIdAndPoints(String exerciseId, int points);
}
