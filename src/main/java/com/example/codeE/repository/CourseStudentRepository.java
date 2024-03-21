package com.example.codeE.repository;

import com.example.codeE.model.course.CourseStudent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface CourseStudentRepository extends JpaRepository<CourseStudent, String> {

    String deleteByStudentIdAndCourseIdSql =  "DELETE FROM course_student cs WHERE cs.student_id = ?1 AND cs.course_id = ?2";
    @Modifying
    @Transactional
    @Query(value = deleteByStudentIdAndCourseIdSql, nativeQuery = true)
    void deleteByStudentIdAndCourseId(String studentId, String courseId);

    String checkExistingStudentIdAndCourseIdSql = "SELECT COUNT(*) FROM course_student cs WHERE cs.student_id = ?1 AND cs.course_id = ?2";

    @Query(value = checkExistingStudentIdAndCourseIdSql, nativeQuery = true)
    Long existsByStudentIdAndCourseId(String studentId, String courseId);

    String deleteAllStudentsByCourseIdSql = "DELETE FROM course_student cs WHERE cs.course_id = ?1";
    @Modifying
    @Transactional
    @Query(value = deleteAllStudentsByCourseIdSql, nativeQuery = true)
    void deleteAllStudentsByCourseId(String courseId);
    //new u.user_id, u.username, u.name, u.email, u.password, u.role, u.created_date, u.updated_date
}
