package com.example.codeE.repository;

import com.example.codeE.model.exercise.FileExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileExerciseRepository extends MongoRepository<FileExercise, String> {
}
