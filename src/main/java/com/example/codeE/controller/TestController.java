package com.example.codeE.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeE.model.user.User;
import com.example.codeE.ulti.excelHelper.ExcelHelper;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/export")
    public void testExportExcel() {
        
        var wb = new XSSFWorkbook();
        ArrayList<User> list = new ArrayList<>();    
        list.add(new User(UUID.randomUUID().toString(), "hieu", "hieu123@email.com", "de123233", "Student", "createdDate", "updatedDate"));
        list.add(new User(UUID.randomUUID().toString(), "khiem", "khiem123@email.com", "de123123", "Student", "123-23-23", "2323-232-232"));
        ExcelHelper.writeExcel(wb, list, "test.xlsx", "test");
    }
}
