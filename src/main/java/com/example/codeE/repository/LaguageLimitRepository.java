package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.LanguageLimit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LaguageLimitRepository extends MongoRepository<LanguageLimit, String>{
    String findByProblemIdAndLanguageIdSql = "SELECT * FROM language_limit WHERE problem_id = ?1 AND language_id = ?2";

    @Query(value = findByProblemIdAndLanguageIdSql)
    LanguageLimit findByProblemIdAndLanguageId(String problemId, String languageId);
}
