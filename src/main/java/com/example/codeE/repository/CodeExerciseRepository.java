package com.example.codeE.repository;

import com.example.codeE.model.exercise.CodeExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeExerciseRepository extends MongoRepository<CodeExercise, String> {
}
