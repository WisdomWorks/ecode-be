package com.example.codeE.repository;

import com.example.codeE.model.exercise.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
}
