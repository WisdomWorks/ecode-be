package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.service.common.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CourseService extends CommonService<Course> {
    Course updateById(String id, UpdateCourseRequest update);
    boolean importByExcel(MultipartFile file);
}
