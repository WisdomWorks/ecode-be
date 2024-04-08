package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CodeSubmissionRepository extends MongoRepository<CodeSubmission, String>
{
    List<CodeSubmission> getCodeSubmissionByExerciseId(String exerciseId);
}
