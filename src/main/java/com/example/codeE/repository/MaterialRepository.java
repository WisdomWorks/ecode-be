package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, String> {
    String findByTopicIdSql = "SELECT * FROM material WHERE topic_id = ?1";
    String findByMaterialIdAndTopicIdSql = "SELECT * FROM material WHERE material_id = ?1 AND topic_id = ?2";
    String getAllGroupsByMaterialIdSql = "SELECT * FROM codee.view_permission_material v WHERE v.material_id = ?1";
    String addViewPermissionSql = "INSERT INTO view_permission_material (material_id, group_id) VALUES (?1, ?2)";
    String removeViewPermissionSql = "DELETE FROM view_permission_material WHERE material_id = ?1 AND group_id = ?2";

    @Query(value = findByTopicIdSql, nativeQuery = true)
    List<Material> findByTopicId(String topicId);

    @Query(value = findByMaterialIdAndTopicIdSql, nativeQuery = true)
    Material findByMaterialIdAndTopicId(String materialId, String topicId);


    @Query(value = getAllGroupsByMaterialIdSql, nativeQuery = true)
    List<Group> getAllGroupsByMaterialId(String materialId);

    @Modifying
    @Transactional
    @Query(value= addViewPermissionSql, nativeQuery = true)
    void addViewPermission(String materialId, String groupId);

    @Modifying
    @Transactional
    @Query(value= removeViewPermissionSql, nativeQuery = true)
    void removeViewPermission(String materialId, String groupId);
}
