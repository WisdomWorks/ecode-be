package com.example.codeE.service.courseStudent;

import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateStudentsToCourseRequest;

import java.util.List;

public interface CourseStudentService {
    List<CourseStudent> addStudentToCourse(AddStudentToCourseRequest request);
    List<CourseStudent> updateStudentsInCourse(UpdateStudentsToCourseRequest request);
    List<String> importStudentsToCourse(ImportStudentToCourseRequest request);
    Boolean deleteStudentInCourse(RemoveStudentFromCourseRequest request);
    Boolean checkStudentInCourse(String studentId, String courseId);
}
