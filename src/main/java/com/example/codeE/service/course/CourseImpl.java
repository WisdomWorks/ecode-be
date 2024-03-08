package com.example.codeE.service.course;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.CourseTeacherRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.CourseResponse;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CourseImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseTeacherRepository courseTeacherRepository;
    @Override
    public CourseResponse createOne(CreateCourseRequest courseRequest) {
        try {
            String courseId = UUID.randomUUID().toString();
            var course = new Course(courseRequest, courseId);
            var courseSaved = this.courseRepository.save(course);

            var teacherInCourse = new CourseTeacher(courseRequest.getTeacherId(), courseSaved.getCourseId(), true);
            var teacher = this.courseTeacherRepository.save(teacherInCourse);
            return new
                    CourseResponse(
                            courseSaved,
                            this.userRepository.findById(teacher.getTeacherId()).orElseThrow(() -> new NoSuchElementException("No Teacher found with ID:" + teacher.getTeacherId()))
            );
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return null;
        }
    }

    @Override
    public CourseResponse getById(String courseId) {
        var course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("No course found with ID:" + courseId));
        List<User> studentInCourse = this.userRepository.getUserInCourse(courseId);
        System.out.println(studentInCourse);
        var teacher = this.userRepository.getTeacherInCourse(courseId);
        System.out.println(teacher);
        return new CourseResponse(course, studentInCourse, teacher);
    }

    @Override
    public List<CourseResponse> getAll() {
        var result = new ArrayList<CourseResponse>();
        var data = this.courseRepository.findAll();
        for (Course i: data) {
            result.add(new CourseResponse(i));
        }
        return result;
    }

    @Override
    public void deleteById(String courseId) {
        if (courseRepository.existsById(courseId)) {
            this.courseRepository.deleteById(courseId);
        } else {
            throw new NoSuchElementException("Course not found with id " + courseId);
        }
    }

    @Override
    public Course updateById(String id, UpdateCourseRequest update) {
        Course existingCourse = courseRepository.findById(id).get();

        if(update.getCourseName() != null){
            existingCourse.setCourseName(update.getCourseName());
        }
        if (update.getSemester() != null) {
            existingCourse.setSemester(update.getSemester());
        }
        if (update.getDescription() != null) {
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

    @Override
    public Boolean checkCourseExistById(String courseId) {
        return this.courseRepository.findById(courseId).isPresent();
    }
}
