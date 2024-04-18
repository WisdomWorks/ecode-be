package com.example.codeE.repository;

import com.example.codeE.model.exercise.common.SessionExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionExerciseRepository extends MongoRepository<SessionExercise, String> {
    List<SessionExercise> findByStudentIdAndLoginId(String studentId, String loginId);
    List<SessionExercise> findByStudentId(String studentId);
}
