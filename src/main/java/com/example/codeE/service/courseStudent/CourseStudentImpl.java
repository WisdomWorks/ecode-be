package com.example.codeE.service.courseStudent;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.course.StudentInCourseExcel;
import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.user.Student;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseStudentImpl implements CourseStudentService {
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseStudent addStudentToCourse(AddStudentToCourseRequest request) {
        try {
            CourseStudent courseStudent = new CourseStudent(request);
            return this.courseStudentRepository.save(courseStudent);
        }catch (Exception e) {
            LoggerHelper.logInfo(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean importStudentsToCourse(ImportStudentToCourseRequest request) {

        MultipartFile file = request.getFile();

        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<CourseStudent> list = new ArrayList<>();
                List<StudentInCourseExcel> importedStudents = ExcelHelper.getAllFieldsFromExcel(file.getInputStream(), StudentInCourseExcel.class);
                LoggerHelper.logInfo("Number of imported students: " + importedStudents.size());
                System.out.println(importedStudents);
                for (StudentInCourseExcel excelStudent : importedStudents) {
                    User student = userRepository.findUserByRoleAndUserName("student", excelStudent.getStudentName());
                    if (student != null) {
                        list.add(new CourseStudent(student.getUserId(), request.getCourseId()));
                    } else {
                        // Log the error and continue processing
                        LoggerHelper.logError("Student not found for username: " + excelStudent.getStudentName());
                    }
                }
                if (!list.isEmpty()) {
                    this.courseStudentRepository.saveAll(list);
                }
                return true;
            } catch (IOException e) {
                LoggerHelper.logError("IOException is found: " + e.getMessage());
                return false;
            } catch (Exception ex) {
                LoggerHelper.logError("Exception is found: " + ex.getMessage());
                LoggerHelper.logInfo("Fail to add student into the course.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteStudentInCourse(RemoveStudentFromCourseRequest request) {
        try {
            courseStudentRepository.deleteByStudentIdAndCourseId(request.getStudentId(),request.getCourseId());
            return true;
        } catch (Exception e) {
            // Handle any exceptions
            LoggerHelper.logError("Failed to delete", e);
            return false;
        }
    }
}
