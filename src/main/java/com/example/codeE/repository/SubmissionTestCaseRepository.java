package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubmissionTestCaseRepository extends MongoRepository<SubmissionTestCase, String> {
    List<SubmissionTestCase> findBySubmissionId(String submissionId);
}
