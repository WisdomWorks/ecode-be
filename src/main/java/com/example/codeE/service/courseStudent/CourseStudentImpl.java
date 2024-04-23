package com.example.codeE.service.courseStudent;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateStudentsToCourseRequest;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseStudentImpl implements CourseStudentService {
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ArrayList<CourseStudent> addStudentToCourse(AddStudentToCourseRequest request) {
        var result = new ArrayList<CourseStudent>();
        try {
            for(String studentId : request.getStudentIds()){
                CourseStudent courseStudent = new CourseStudent(studentId, request.getCourseId());
                result.add(this.courseStudentRepository.save(courseStudent));
            }
            return result;
        }catch (Exception e) {
            LoggerHelper.logInfo(e.getMessage());
            return result;
        }
    }

    @Override
    public List<CourseStudent> updateStudentsInCourse(UpdateStudentsToCourseRequest request) {
        courseStudentRepository.deleteAllStudentsByCourseId(request.getCourseId());
        var result = new ArrayList<CourseStudent>();
        try {
            for(String studentId : request.getStudentIds()){
                CourseStudent courseStudent = new CourseStudent(studentId, request.getCourseId());
                result.add(this.courseStudentRepository.save(courseStudent));
            }
            return result;
        }catch (Exception e) {
            LoggerHelper.logInfo(e.getMessage());
            return result;
        }
    }

    @Override
    public List<Integer> importStudentsToCourse(ImportStudentToCourseRequest request) {
        MultipartFile file = request.getFile();
        List<Integer> failedRows = new ArrayList<>();

        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<String> importedStudentsName = getAllNamesFromExcel(file.getInputStream());
                LoggerHelper.logInfo("Number of imported students: " + importedStudentsName.size());

                for (String excelStudent : importedStudentsName) {
                    System.out.println(excelStudent);
                }
                for (int i = 0; i < importedStudentsName.size(); i++) {
                    String excelStudentName = importedStudentsName.get(i);
                    User student = userRepository.findUserByRoleAndUserName("student", excelStudentName);
                    if (student == null) {
                        failedRows.add(i + 2); // Adding 2 to account for the header row and 0-based index
                        LoggerHelper.logError("Student not found for username: " + excelStudentName);
                    }
                }
                if (failedRows.isEmpty()) {
                    List<CourseStudent> list = new ArrayList<>();
                    for (String excelStudentName : importedStudentsName) {
                        User student = userRepository.findUserByRoleAndUserName("student", excelStudentName);
                        CourseStudent courseStudent = new CourseStudent(student.getUserId(), request.getCourseId());
                        list.add(courseStudent);
                    }
                    this.courseStudentRepository.saveAll(list);
                }
            } catch (IOException e) {
                LoggerHelper.logError("IOException is found: " + e.getMessage());
            } catch (Exception ex) {
                LoggerHelper.logError("Exception is found: " + ex.getMessage());
                LoggerHelper.logInfo("Fail to add student into the course.");
            }
        }
        return failedRows;
    }

    @Override
    public Boolean deleteStudentInCourse(RemoveStudentFromCourseRequest request) {
        try {
            courseStudentRepository.deleteByStudentIdAndCourseId(request.getStudentId(),request.getCourseId());
            return true;
        } catch (Exception e) {
            // Handle any exceptions
            LoggerHelper.logError("Failed to delete", e);
            return false;
        }
    }
    @Override
    public Boolean checkStudentInCourse(String studentId, String courseId){
        return this.courseStudentRepository.existsByStudentIdAndCourseId(studentId, courseId) > 0;
    }
    public static List<String> getAllNamesFromExcel(InputStream inputStream) {
        List<String> names = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }
                Cell cell = nextRow.getCell(0); // Assuming the name is in the first column (index 0)
                if (cell != null) {
                    String name = cell.getStringCellValue().trim();
                    if (!name.isEmpty()) {
                        names.add(name);
                    }
                }
            }
        } catch (IOException e) {
            LoggerHelper.logError("IOException is found: " + e.getMessage());
        }
        return names;
    }
}
