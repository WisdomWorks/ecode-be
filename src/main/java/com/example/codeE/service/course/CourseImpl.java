package com.example.codeE.service.course;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.course.Course;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.request.course.UpdateCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createOne(Course course) {
        try {
            String courseId = UUID.randomUUID().toString();
            course.setCourseId(courseId);
            return this.courseRepository.save(course);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return null;
        }
    }

    @Override
    public Course getById(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }

//    @Override
//    public Course updateById(String id, Map<String, Object> update) {
//        Optional<Course> optionalCourse = courseRepository.findById(id);
//        if (optionalCourse.isEmpty()) {
//            return null;
//        }
//
//        Course existingCourse = optionalCourse.get();
//
//        for (Map.Entry<String, Object> entry : update.entrySet()) {
//            String propertyName = entry.getKey();
//            Object newValue = entry.getValue();
//            try {
//                Field field = existingCourse.getClass().getDeclaredField(propertyName);
//                field.setAccessible(true);
//                field.set(existingCourse, newValue);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                LoggerHelper.logError("Invalid property encountered while updating object", e);
//            }
//        }
//
//        return courseRepository.save(existingCourse);
//    }

    @Override
    public boolean deleteById(String courseId) {
        if (courseRepository.existsById(courseId)) {
            this.courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    @Override
    public Course updateById(String id, UpdateCourseRequest update) {
        Course existingCourse = courseRepository.findById(id).get();

        if(update.getCourseName() != null){
            existingCourse.setCourseName(update.getCourseName());
        } else if (update.getSemester() != null) {
            existingCourse.setSemester(update.getSemester());
        } else if (update.getDescription() != null) {
            existingCourse.setDescription(update.getDescription());
        }


        return courseRepository.save(existingCourse);
    }

    @Override
    public boolean importByExcel(MultipartFile file) {
        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<Course> courses = new ArrayList<>();
                List<CourseFromExcel> importedCourse = ExcelHelper.importFromExcel(file.getInputStream(), CourseFromExcel.class);
                for (CourseFromExcel course : importedCourse) {
                    courses.add(new Course(course));
                }
                this.courseRepository.saveAll(courses);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
