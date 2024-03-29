package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.RuntimeVersion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuntimeVersionRepository extends MongoRepository<RuntimeVersion, String>{
}
