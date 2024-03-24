package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeExerciseRepositoryWBD extends MongoRepository<CodeExerciseWBD, String> {
}
