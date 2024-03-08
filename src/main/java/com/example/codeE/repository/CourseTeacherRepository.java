package com.example.codeE.repository;

import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, String> {

}
