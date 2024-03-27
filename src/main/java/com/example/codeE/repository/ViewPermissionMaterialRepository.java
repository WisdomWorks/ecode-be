package com.example.codeE.repository;

import com.example.codeE.model.material.ViewPermissionMaterial;
import com.example.codeE.model.topic.ViewPermissionTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ViewPermissionMaterialRepository extends JpaRepository<ViewPermissionMaterial, String> {
    @Query(value = "SELECT v.material_id, v.group_id  FROM codee.view_permission_material v WHERE v.material_id = ?1", nativeQuery = true)
    List<ViewPermissionMaterial> getAllGroupsByMaterialId(String materialId);

    String addViewPermissionSql = "INSERT INTO view_permission_material (material_id, group_id) VALUES (?1, ?2)";
    String removeViewPermissionSql = "DELETE FROM view_permission_material WHERE material_id = ?1 AND group_id = ?2";
    @Modifying
    @Transactional
    @Query(value= addViewPermissionSql, nativeQuery = true)
    void addViewPermission(String materialId, String groupId);

    @Modifying
    @Transactional
    @Query(value= removeViewPermissionSql, nativeQuery = true)
    void removeViewPermission(String materialId, String groupId);
}
