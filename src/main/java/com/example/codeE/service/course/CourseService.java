package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.request.course.CourseResponse;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.service.common.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CourseService extends CommonService<CourseResponse, CreateCourseRequest> {
    Course updateById(String id, UpdateCourseRequest update);
    ResponseEntity<Map<String, String>> importByExcel(MultipartFile file);
    Boolean checkCourseExistById(String groupId);
}
