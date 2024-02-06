package com.example.codeE.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.user.User;

@SpringBootTest
public class ExcelHelperTest {
    @Test
    void testExportExcel() {
        var wb = new XSSFWorkbook();
        ArrayList<User> list = new ArrayList<>();
        String filePath = "test.xlsx";
        list.add(new User(UUID.randomUUID().toString(), "hieu", "hieu123@email.com", "de123233", "Student",
                "createdDate", "updatedDate"));
        list.add(new User(UUID.randomUUID().toString(), "khiem", "khiem123@email.com", "de123123", "Student",
                "123-23-23", "2323-232-232"));
        ExcelHelper.writeExcel(wb, list, filePath, "test");
        assertTrue(new File(Constant.EXCEL_FILE_PATH + filePath).exists());
    }

    @Test
    void testImportExcel() {

    }
}
