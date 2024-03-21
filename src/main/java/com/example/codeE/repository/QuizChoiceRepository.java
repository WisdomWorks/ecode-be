package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.QuizChoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizChoiceRepository extends MongoRepository<QuizChoice, String> {
}
