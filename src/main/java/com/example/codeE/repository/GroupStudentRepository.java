package com.example.codeE.repository;

import com.example.codeE.entity.GetGroupStudentEntity;
import com.example.codeE.model.group.GroupStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupStudentRepository extends JpaRepository<GroupStudent, String> {
     String getUserInCourseQuery = "SELECT s.user_id, s.name, s.username, gs.join_date FROM codee.user s JOIN codee.group_student gs ON s.user_id = gs.student_id WHERE gs.group_id = ?1";
     @Query(value = getUserInCourseQuery, nativeQuery = true)
     List<GetGroupStudentEntity> getStudentInGroup(String groupId);
}
