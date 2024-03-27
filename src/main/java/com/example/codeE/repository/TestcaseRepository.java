package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestcaseRepository extends MongoRepository<TestCase, String> {
}
