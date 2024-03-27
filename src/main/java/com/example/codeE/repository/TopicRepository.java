package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.topic.ViewPermissionTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query(value = "SELECT t FROM topic t WHERE t.courseId = ?1")
    List<Topic> getAllTopicsByCourseId(String courseId);

    @Query(value =  "SELECT * FROM codee.topic " +
                    "WHERE (is_show_all = true OR topic_id IN " +
                    "   (SELECT topic_id FROM codee.view_permission_topic " +
                    "   WHERE group_id IN " +
                    "       (SELECT group_id FROM codee.group_student " +
                    "       WHERE student_id = ?1 ))) " +
                    "AND course_id = ?2 ; ", nativeQuery = true)
    List<Topic> getTopicByUser(String studentId, String courseId);
}
