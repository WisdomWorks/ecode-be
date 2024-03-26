package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeSubmissionRepository extends MongoRepository<CodeSubmission, String>
{
    String findBySubmissionIdAndStatusSql = "SELECT * FROM code_submission WHERE submission_id = ?1 AND status = ?2;";
    CodeSubmission findBySubmissionIdAndStatus(String submissionId, String searchedStatus);
}
