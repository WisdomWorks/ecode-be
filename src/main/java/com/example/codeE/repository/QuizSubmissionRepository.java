package com.example.codeE.repository;

import com.example.codeE.model.exercise.QuizSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizSubmissionRepository  extends MongoRepository<QuizSubmission, String> {
}
