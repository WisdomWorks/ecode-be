package com.example.codeE.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.codeE.model.user.User;

@SpringBootTest
public class ExcelHelperTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2024-01-28 05:10:52", formatter);
    @Test
    void testExportExcel() {
        var wb = new XSSFWorkbook();
        ArrayList<User> list = new ArrayList<>();
        String filePath = "test.xlsx";
        list.add(new User(UUID.randomUUID().toString(), "hieu", "hieu123@email.com", "de123233", "Student",
                dateTime, dateTime));
        list.add(new User(UUID.randomUUID().toString(), "khiem", "khiem123@email.com", "de123123", "Student",
                dateTime, dateTime));
        var filePathReturn =ExcelHelper.writeExcel(wb, list, filePath, "test");
        assert(new File(filePathReturn).exists());
    }

    @Test
    void testImportExcel() {

    }
}
