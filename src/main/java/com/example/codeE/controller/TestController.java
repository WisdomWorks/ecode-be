package com.example.codeE.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeE.model.user.Admin;
import com.example.codeE.ulti.excelHelper.ExcelHelper;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/export")
    public void testExportExcel() {
        
        var wb = new XSSFWorkbook();
        ArrayList<Admin> list = new ArrayList<>();
        list.add(new Admin(UUID.randomUUID().toString(), "asd", "abc", "abc", "abc", "abc"));
        //add data to list
        list.add(new Admin(UUID.randomUUID().toString(), "asd", "abc", "abc", "abc", "abc"));      
        ExcelHelper.writeExcel(wb, list, "test.xlsx", "test");
    }
    
    public void testImportExcel() {
        // ExcelHelper.importFromExcel("test.xlsx", Admin.class);
    }
}
