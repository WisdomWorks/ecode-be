package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.service.common.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface CourseService extends CommonService<Course> {
    Course updateById(String id, Map<String, Object> update);
    boolean importByExcel(MultipartFile file);
}
