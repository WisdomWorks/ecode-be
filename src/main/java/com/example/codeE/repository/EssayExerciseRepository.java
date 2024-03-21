package com.example.codeE.repository;

import com.example.codeE.model.exercise.EssayExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EssayExerciseRepository extends MongoRepository<EssayExercise, String>{
}
