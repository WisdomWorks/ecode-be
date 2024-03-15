package com.example.codeE.repository;

import com.example.codeE.model.course.CourseTeacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, String> {
    String deleteTeacherByCourseIdSql = "DELETE FROM course_teacher ct WHERE ct.course_id = ?1";
    @Modifying
    @Transactional
    @Query(value = deleteTeacherByCourseIdSql, nativeQuery = true)
    void deleteTeacherInCourseByCourseId(String courseId);
}
