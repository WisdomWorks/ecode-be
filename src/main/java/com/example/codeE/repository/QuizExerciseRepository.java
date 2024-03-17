package com.example.codeE.repository;

import com.example.codeE.model.exercise.QuizExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizExerciseRepository extends MongoRepository<QuizExercise, String> {
}
