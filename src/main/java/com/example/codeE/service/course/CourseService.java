package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.user.User;
import com.example.codeE.request.course.*;
import com.example.codeE.service.common.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CourseService extends CommonService<CourseResponse, CreateCourseRequest> {
    Course updateById(String id, UpdateCourseRequest update);
    ResponseEntity<Map<String, Object>> importByExcel(MultipartFile file);
    Boolean checkCourseExistById(String groupId);
    CourseEnrollmentResponse<CourseTeacherResponse> enrollStudentToCourse(CourseEnrollmentRequest request);
    void unEnrollUserInCourse(String userId, String courseId);
    List<Course> getCourseByUserId(String userId);
    List<Course> getCourseByStudentId(String userId);
    List<Course> getCourseByTeacherId(String userId);
    List<User> getStudentsByCourseId(String courseId);
}
