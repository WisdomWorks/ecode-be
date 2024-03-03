package com.example.codeE.service.courseStudent;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.mapper.course.StudentInCourseExcel;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
    public List<String> importStudentsToCourse(ImportStudentToCourseRequest request) {

        MultipartFile file = request.getFile();
        List<String> result = new ArrayList<>();

        if (ExcelHelper.isValidExcelFile(file)) {
            try {
                List<CourseStudent> list = new ArrayList<>();
                List<String> importedStudentsName = getAllNamesFromExcel(file.getInputStream());
                LoggerHelper.logInfo("Number of imported students: " + importedStudentsName.size());

                for (String excelStudent : importedStudentsName) {
                    System.out.println(importedStudentsName.toString());
                }
                for (String excelStudentName : importedStudentsName) {
                    User student = userRepository.findUserByRoleAndUserName("student", excelStudentName);
                    if (student != null) {
                        list.add(new CourseStudent(student.getUserId(), request.getCourseId()));
                    } else {
                        // Log the error and continue processing
                        result.add(excelStudentName);
                        LoggerHelper.logError("Student not found for username: " + excelStudentName);
                    }
                }
                this.courseStudentRepository.saveAll(list);
                return result;
            } catch (IOException e) {
                LoggerHelper.logError("IOException is found: " + e.getMessage());
            } catch (Exception ex) {
                LoggerHelper.logError("Exception is found: " + ex.getMessage());
                LoggerHelper.logInfo("Fail to add student into the course.");
            }
        }
        return result;
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
            e.printStackTrace();
        }
        return names;
    }


}
