package com.example.codeE.repository;

import com.example.codeE.model.exercise.MSExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MSExerciseRepository extends JpaRepository<MSExercise, String> {
    @Query(value = "SELECT new com.example.codeE.model.exercise.MSExercise(e.exerciseId, e.topicId) "
                    + "FROM exercise e "
                    + "JOIN e.topic t "
                    + "JOIN t.course c "
                    + "WHERE c.courseId = ?1")
    List<MSExercise> getAllExercisesByCourseId(String courseId);
}
