package com.example.codeE.service.courseStudent;

import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;

import java.util.ArrayList;
import java.util.List;

public interface CourseStudentService {
    List<CourseStudent> addStudentToCourse(AddStudentToCourseRequest request);
    List<String> importStudentsToCourse(ImportStudentToCourseRequest request);
    boolean deleteStudentInCourse(RemoveStudentFromCourseRequest request);
}
