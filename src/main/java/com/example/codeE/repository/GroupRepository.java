package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String>{
    @Query(value = "SELECT g.group_id, g.course_id, g.group_name, g.created_date, g.updated_date FROM codee.group g WHERE g.course_id = ?1 ", nativeQuery = true)
    List<Group> getAllCourseByCourseId(String courseId);
}
