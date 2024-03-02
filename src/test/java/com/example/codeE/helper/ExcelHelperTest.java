package com.example.codeE.helper;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.codeE.constant.Constant;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.codeE.model.user.User;

@SpringBootTest
public class ExcelHelperTest {

    private List<User> mockDataUser;
    @BeforeEach
    void setUp() {
        this.mockDataUser = new ArrayList<>();
        this.mockDataUser.add(new User("usr1", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr2", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr3", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr4", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr5", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
    }

    @Test
    void testExportExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String fileName = "TestExportExcel.xlsx";
        String sheetName = "Users";
        //check exists file if yes then delete and create new one
        var checkFile = new File(Constant.EXCEL_FILE_PATH + fileName);
        if(checkFile.exists()){
            checkFile.delete();
        }
        String filePath = ExcelHelper.writeExcel(workbook, mockDataUser, fileName, sheetName);
        File file = new File(filePath);
        assertTrue(file.exists());
    }

    @Test
    void testImportExcel() throws IOException {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        String fileName = "TestExportExcel.xlsx";
//        String sheetName = "Users";
//        String filePath = ExcelHelper.writeExcel(workbook, mockDataUser, fileName, sheetName);
//        File file = new File(filePath);
//        FileInputStream input = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile("TestExportExcel.xlsx",
//                file.getName(), "text/plain", IOUtils.toByteArray(input));
//        List<User> users = new ArrayList<>();
//        List<UserFromExcel> importedUsers = ExcelHelper.importFromExcel(multipartFile.getInputStream(), UserFromExcel.class);
//        for (UserFromExcel excelUser : importedUsers) {
//            users.add(new User(excelUser));
//        }
//        assertTrue(users.size() > 0);
    }
}
