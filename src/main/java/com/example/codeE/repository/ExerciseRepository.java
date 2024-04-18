package com.example.codeE.repository;

import com.example.codeE.model.exercise.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    String getAllExercisesByTopicIdSql = "SELECT * FROM exercise WHERE topic_id = ?1";

    @Query(value = getAllExercisesByTopicIdSql, nativeQuery = true)
    List<Exercise> getAllExercisesByTopicId(String courseId);
}
