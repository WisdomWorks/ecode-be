package com.example.codeE.repository;

import com.example.codeE.model.exercise.EssaySubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EssaySubmissionRepository extends MongoRepository<EssaySubmission, String>{
}
