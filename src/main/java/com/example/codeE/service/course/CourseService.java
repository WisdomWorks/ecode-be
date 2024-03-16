package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.request.course.*;
import com.example.codeE.service.common.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CourseService extends CommonService<CourseResponse, CreateCourseRequest> {
    Course updateById(String id, UpdateCourseRequest update);
    ResponseEntity<Map<String, String>> importByExcel(MultipartFile file);
    Boolean checkCourseExistById(String groupId);
    CourseEnrollmentResponse<CourseTeacherResponse> enrollStudentToCourse(CourseEnrollmentRequest request);
    List<Course> getCourseByStudentId(String userId);
    List<Course> getCourseByTeacherId(String userId);
}
