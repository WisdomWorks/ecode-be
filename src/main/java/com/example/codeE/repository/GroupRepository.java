package com.example.codeE.repository;

import com.example.codeE.model.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String>{
    @Query(value = "select new com.example.codeE.model.group.Group (g.groupId, g.courseId, g.groupName, g.createDate, g.updateDate) from group g where g.courseId = ?1")
    List<Group> getAllCourseByCourseId(String courseId);
}
