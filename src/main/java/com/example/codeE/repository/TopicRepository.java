package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query(value = "SELECT t FROM topic t WHERE t.courseId = ?1")
    List<Topic> getAllTopicsByCourseId(String courseId);

    @Query(value = "SELECT * FROM codee.view_permission_topic v WHERE v.topic_id = ?1", nativeQuery = true)
    List<Group> getAllGroupsByTopicId(String topicId);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO codee.view_permission_topic (topic_id, group_id) VALUES (?1, ?2)", nativeQuery = true)
    void addViewPermission(String topicId, String groupId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM codee.view_permission_topic WHERE topic_id = ?1 AND group_id = ?2", nativeQuery = true)
    void removeViewPermission(String topicId, String groupId);

    @Query(value =  "SELECT * FROM codee.topic " +
                    "WHERE topic_id IN " +
                    "   (SELECT topic_id FROM codee.view_permission_topic " +
                    "   WHERE group_id IN " +
                    "       (SELECT group_id FROM codee.group_student " +
                    "       WHERE student_id = ?1 )) " +
                    "AND course_id = ?2 ; ", nativeQuery = true)
    List<Topic> getTopicByUser(String studentId, String courseId);
}
