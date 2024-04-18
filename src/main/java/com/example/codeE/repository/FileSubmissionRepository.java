package com.example.codeE.repository;

import com.example.codeE.model.exercise.FileSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileSubmissionRepository extends MongoRepository<FileSubmission, String> {
}
