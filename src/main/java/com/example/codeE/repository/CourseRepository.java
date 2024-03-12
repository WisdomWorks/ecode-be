package com.example.codeE.repository;

import com.example.codeE.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    String getCourseByUserId = "SELECT c.course_id, c.course_name, c.semester, c.description, c.enroll_key, c.created_date, c.updated_date " +
            "FROM codee.course c " +
            "JOIN codee.course_student sc ON c.course_id = sc.course_id " +
            "JOIN codee.user s ON sc.student_id = s.user_id " +
            "WHERE s.user_id = ?1 ;";
    @Query(value = getCourseByUserId, nativeQuery = true)
    List<Course> getCourseByStudentId(String userId);

    String getCourseByTeacherId = "SELECT c.course_id, c.course_name, c.semester, c.description, c.enroll_key, c.created_date, c.updated_date " +
            "FROM codee.course c " +
            "JOIN codee.course_teacher ct ON c.course_id = ct.course_id " +
            "JOIN codee.user s ON ct.teacher_id = s.user_id " +
            "WHERE s.user_id = ?1 AND ct.is_main = true; ";
    @Query(value = getCourseByTeacherId, nativeQuery = true)
    List<Course> getCourseByTeacherId(String userId);
}
