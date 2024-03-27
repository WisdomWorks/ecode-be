package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubmissionTestCaseRepository extends MongoRepository<SubmissionTestCase, String> {
}
