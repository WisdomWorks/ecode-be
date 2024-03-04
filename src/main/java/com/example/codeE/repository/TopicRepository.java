package com.example.codeE.repository;

import com.example.codeE.model.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query(value = "SELECT t FROM topic t WHERE t.courseId = ?1")
    List<Topic> getAllTopicsByCourseId(String courseId);
}
