package com.example.codeE.repository;

import com.example.codeE.entity.group.StudentInGroupEntity;
import com.example.codeE.entity.group.StudentNotInGroupEntity;
import com.example.codeE.model.group.GroupStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupStudentRepository extends JpaRepository<GroupStudent, String> {
     String getUserInCourseQuery = "SELECT new com.example.codeE.entity.group.StudentInGroupEntity(s.userId, s.name, s.username, gs.joinDate) FROM user s JOIN group_student gs ON s.userId = gs.studentId WHERE gs.groupId = ?1";
     String getUserInNotCourseQuery = "SELECT new com.example.codeE.entity.group.StudentNotInGroupEntity(s.userId, s.name, s.username) " +
             "FROM user s LEFT JOIN group_student gs ON s.userId = gs.studentId AND gs.groupId = ?1 " +
             "WHERE gs.groupId IS NULL";
     String deleteStudentInGroup = "DELETE FROM codee.group_student gs WHERE gs.student_id = ?1 AND gs.group_id = ?2 ";
     @Query(value = getUserInCourseQuery)
     List<StudentInGroupEntity> getStudentInGroup(String groupId);

     @Query(value = getUserInNotCourseQuery)
     List<StudentNotInGroupEntity> getStudentNotInGroup(String groupId);

     @Query(value = deleteStudentInGroup, nativeQuery = true)
     void deleteStudentInGroup(String studentId, String GroupId);
}
