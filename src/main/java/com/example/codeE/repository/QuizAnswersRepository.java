package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.QuizAnswers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizAnswersRepository extends MongoRepository<QuizAnswers, String> {
}
