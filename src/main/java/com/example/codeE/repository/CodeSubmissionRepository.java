package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeSubmissionRepository extends MongoRepository<CodeSubmission, String>
{
}
