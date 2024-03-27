package com.example.codeE.repository;

import com.example.codeE.model.topic.ViewPermissionTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ViewPermissionTopicRepository extends JpaRepository<ViewPermissionTopic, String> {
    @Query(value = "SELECT v.topic_id, v.group_id  FROM codee.view_permission_topic v WHERE v.topic_id = ?1", nativeQuery = true)
    List<ViewPermissionTopic> getAllGroupsByTopicId(String topicId);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO codee.view_permission_topic (topic_id, group_id) VALUES (?1, ?2)", nativeQuery = true)
    void addViewPermission(String topicId, String groupId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM codee.view_permission_topic WHERE topic_id = ?1 AND group_id = ?2", nativeQuery = true)
    void removeViewPermission(String topicId, String groupId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM codee.view_permission_topic WHERE topic_id = ?1", nativeQuery = true)
    void removeViewPermissionByTopicId(String topicId);

}
