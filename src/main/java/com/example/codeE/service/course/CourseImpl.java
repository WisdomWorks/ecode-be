package com.example.codeE.service.course;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.course.Course;
import com.example.codeE.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class CourseImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createOne(Course course) {
        String courseId = UUID.randomUUID().toString();
        course.setCourseId(courseId);
        return this.courseRepository.save(course);
    }

    @Override
    public Course getById(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course updateById(String id, Map<String, Object> update) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return null;
        }

        Course existingCourse = optionalCourse.get();

        for (Map.Entry<String, Object> entry : update.entrySet()) {
            String propertyName = entry.getKey();
            Object newValue = entry.getValue();
            try {
                Field field = existingCourse.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(existingCourse, newValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LoggerHelper.logError("Invalid property encountered while updating object", e);
            }
        }

        return courseRepository.save(existingCourse);
    }

    @Override
    public boolean deleteById(String courseId) {
        if(courseRepository.existsById(courseId)){
            this.courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    @Override
    public boolean importByExcel(MultipartFile file) {
        if(ExcelHelper.isValidExcelFile(file)){
            try {
                List<Course> courses = new ArrayList<>();
                List<CourseFromExcel> importedCourse = ExcelHelper.importFromExcel(file.getInputStream(), CourseFromExcel.class);
                for (CourseFromExcel course : importedCourse){
                   courses.add(new Course(course));
                }
                this.courseRepository.saveAll(courses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
