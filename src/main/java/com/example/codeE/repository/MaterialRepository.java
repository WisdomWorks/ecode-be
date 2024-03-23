package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, String> {
    String findByTopicIdSql = "SELECT * FROM material WHERE topic_id = ?1";
    String findByMaterialIdAndTopicIdSql = "SELECT * FROM material WHERE material_id = ?1 AND topic_id = ?2";
    String getAllGroupsByMaterialIdSql = "SELECT * FROM codee.view_permission_material v WHERE v.material_id = ?1";
    String getMaterialByUserId = "SELECT * FROM codee.material WHERE (is_show_all = true OR material_id IN " +
                                 "  (SELECT material_id FROM codee.view_permission_material " +
                                 "  WHERE group_id IN " +
                                 "      (SELECT group_id FROM codee.group_student " +
                                 "      WHERE student_id = ?1 )))" +
                                 "AND topic_id = ?2 ;";
    @Query(value = findByTopicIdSql, nativeQuery = true)
    List<Material> findByTopicId(String topicId);

    @Query(value = findByMaterialIdAndTopicIdSql, nativeQuery = true)
    Material findByMaterialIdAndTopicId(String materialId, String topicId);


//    @Query(value = getAllGroupsByMaterialIdSql, nativeQuery = true)
//    List<Group> getAllGroupsByMaterialId(String materialId);


    @Query(value = getMaterialByUserId, nativeQuery = true)
    List<Material> getMaterialById(String studentId, String topicId);
}
