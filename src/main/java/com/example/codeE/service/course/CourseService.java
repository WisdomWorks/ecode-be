package com.example.codeE.service.course;

import com.example.codeE.model.course.Course;
import com.example.codeE.service.common.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CourseService extends CommonService<Course> {
    boolean importByExcel(MultipartFile file);
}
