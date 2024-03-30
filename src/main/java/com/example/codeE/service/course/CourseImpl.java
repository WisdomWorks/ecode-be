package com.example.codeE.service.course;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.CourseTeacherRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.*;
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
    private CourseStudentRepository courseStudentRepository;
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
        var teacher = this.userRepository.getTeacherInCourse(courseId);
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
    public Course updateById(String id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.setCourseName(request.getCourseName());
        course.setSemester(request.getSemester());
        course.setEnrollKey(request.getEnrollKey());
        course.setDescription(request.getDescription());

        // Delete all existing students associated with the course
        courseTeacherRepository.deleteTeacherInCourseByCourseId(id);

        userRepository.findById(request.getTeacherId()).orElseThrow(() -> new NoSuchElementException("No Teacher found with ID:" + request.getTeacherId()));
        // Tạo mới và lưu giáo viên vào bảng `course_teacher`
        CourseTeacher ct = new CourseTeacher(request.getTeacherId(), id, true);
        courseTeacherRepository.save(ct);

        return courseRepository.save(course);
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
    public CourseEnrollmentResponse<CourseTeacherResponse> enrollStudentToCourse(CourseEnrollmentRequest request) {
        var response = new CourseEnrollmentResponse<CourseTeacherResponse>();
        if(this.courseStudentService.checkStudentInCourse(request.studentId, request.courseId)){
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMessage("User already enrolled in course");
            response.setError("Can not enroll in course");
            return response;
        }
        var course = this.courseRepository.findById(request.courseId).orElseThrow(()-> new NoSuchElementException("Can not find course with ID: " + request.courseId));
        if (!course.getEnrollKey().equals(request.enrollmentKey)) {
            response.setMessage("Your key not match, please try again");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Invalid enrollment key");
            return response;
        }
        this.userRepository.findById(request.studentId).orElseThrow(()-> new NoSuchElementException("Can not find student with ID: " + request.studentId));
        try {
            var listUsers = new ArrayList<String>();
            listUsers.add(request.studentId);
            var requestAddStudentToCourse = new AddStudentToCourseRequest(listUsers, request.courseId);
            this.courseStudentService.addStudentToCourse(requestAddStudentToCourse);
            response.setMessage( "Enroll student to course successfully");
            response.setStatus(HttpStatus.CREATED.value());
            var result = new CourseTeacherResponse(
                course.getCourseId(),
                    course.getCourseName(),
                    course.getSemester(),
                    course.getDescription(),
                    course.getCreatedDate(),
                    course.getUpdatedDate(),
                    this.userRepository.getTeacherInCourse(course.getCourseId())
            );
            response.setValue(result);
            return response;
        } catch (Exception e) {
            response.setMessage("Failed to enroll student to course");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(e.getMessage());
            return response;
        }
    }

    @Override
    public void unEnrollUserInCourse(String userId, String courseId) {
        var user = this.userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Can not find student with ID: " + userId));
        this.courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Can not find course with ID: " + courseId));
        try {
            if (user.getRole().equals("student"))
                this.courseStudentRepository.deleteByStudentIdAndCourseId(userId, courseId);
            else
                this.courseTeacherRepository.deleteByStudentIdAndCourseId(userId, courseId);
        } catch (Exception e) {
            throw new RuntimeException("student can not unenroll course now.");
        }
    }

    @Override
    public List<Course> getCourseByUserId(String userId) {
        var user = this.userRepository.findById(userId).orElseThrow(() ->new NoSuchElementException("Can not find student with ID: " + userId));
        if (user.getRole().equals("student")){
            return this.courseRepository.getCourseByStudentId(userId);
        }else
            return this.courseRepository.getCourseByTeacherId(userId);
    }

    @Override
    public List<Course> getCourseByStudentId(String userId) {
        this.userRepository.findById(userId).orElseThrow(() ->new NoSuchElementException("Can not find student with ID: " + userId));
        return this.courseRepository.getCourseByStudentId(userId);
    }

    @Override
    public List<Course> getCourseByTeacherId(String userId) {
        this.userRepository.findById(userId).orElseThrow(() ->new NoSuchElementException("Can not find teacher with ID: " + userId));
        return this.courseRepository.getCourseByTeacherId(userId);
    }
}
