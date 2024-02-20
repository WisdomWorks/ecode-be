package com.example.codeE.repository;

import com.example.codeE.model.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query(value = "SELECT new com.example.codeE.model.topic.Topic(t.topicId)"
            + "FROM topic t INNER JOIN t.course WHERE t.courseId = ?1")
    List<Topic> getAllTopicsByCourseId(String courseId);
}
