package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.QuizQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizQuestionRepository  extends MongoRepository<QuizQuestion, String> {
}
