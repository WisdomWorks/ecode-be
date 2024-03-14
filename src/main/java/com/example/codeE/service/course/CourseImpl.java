package com.example.codeE.service.course;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.CourseTeacherRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.CourseEnrollmentRequest;
import com.example.codeE.request.course.CourseEnrollmentResponse;
import com.example.codeE.request.course.CourseResponse;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.service.courseStudent.CourseStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CourseImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseTeacherRepository courseTeacherRepository;
    @Autowired
    private CourseStudentService courseStudentService;
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
            List<User> studentInCourse = this.userRepository.getUserInCourse(i.getCourseId());
            var teacher = this.userRepository.getTeacherInCourse(i.getCourseId());
            result.add(new CourseResponse(i,studentInCourse, teacher));
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
    public ResponseEntity<Map<String, String>> importByExcel(MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            List<Course> courses = new ArrayList<>();
            List<String> unsuccessfulCourses = new ArrayList<>();
            List<CourseFromExcel> importedCourses = ExcelHelper.importFromExcel(file.getInputStream(), CourseFromExcel.class);
            for (CourseFromExcel excelCourse : importedCourses) {
                try {
                    courses.add(new Course(excelCourse));
                    courseRepository.save(courses.get(courses.size() - 1));
                } catch (Exception ex) {
                    unsuccessfulCourses.add(excelCourse.getCourseName());
                    LoggerHelper.logError("Error saving course to database", ex);
                }
            }
            response.put("message", "Courses saved successfully");
            response.put("unsuccessfulCourses", unsuccessfulCourses.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            LoggerHelper.logError("Error processing the file", e);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean checkCourseExistById(String courseId) {
        return this.courseRepository.findById(courseId).isPresent();
    }
    @Override
    public CourseEnrollmentResponse<CourseStudent> enrollStudentToCourse(CourseEnrollmentRequest request) {
        var response = new CourseEnrollmentResponse<CourseStudent>();
        if(this.courseStudentService.checkStudentInCourse(request.studentId, request.courseId)){
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMessage("User already enrolled in course");
            response.setError("Can not enroll in course");
            return response;
        }
        var course = this.courseRepository.findById(request.courseId).orElse(null);
        if (course == null) {
            response.setMessage("Course not found");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError("Can not find course with ID: " + request.courseId);
            return response;
        }
        if (!course.getEnrollKey().equals(request.enrollmentKey)) {
            response.setMessage("Your key not match, please try again");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Invalid enrollment key");
            return response;
        }
        var student = this.userRepository.findById(request.studentId).orElse(null);
        if (student == null) {
            response.setMessage( "Student not found");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError("Can not find student with ID: " + request.studentId);
            return response;
        }
        try {
            var listUsers = new ArrayList<String>();
            listUsers.add(request.studentId);
            var requestAddStudentToCourse = new AddStudentToCourseRequest(listUsers, request.studentId);
            var result = this.courseStudentService.addStudentToCourse(requestAddStudentToCourse);
            response.setMessage( "Enroll student to course successfully");
            response.setStatus(HttpStatus.CREATED.value());
            response.setValues(result);
            return response;
        } catch (Exception e) {
            response.setMessage("Failed to enroll student to course");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(e.getMessage());
            return response;
        }
    }

    @Override
    public List<Course> getCourseByStudentId(String userId) {
        return this.courseRepository.getCourseByStudentId(userId);
    }

    @Override
    public List<Course> getCourseByTeacherId(String userId) {
        return this.courseRepository.getCourseByTeacherId(userId);
    }
}
