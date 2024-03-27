package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeExerciseRepository extends MongoRepository<CodeExerciseWBD, String> {
}
