package com.example.codeE.ulti.excelHelper;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {
    private static final String FILEDIRECT = "C:\\Users\\Admin\\Desktop\\CodeE\\src\\main\\java\\com\\example\\codeE\\ulti\\excelHelper\\";  
    public static FileOutputStream  writeExcel(Workbook workbook, List<T> dataList, String filePath, String sheetName){
        try {
            Sheet sheet = workbook.createSheet(sheetName);
            // Create header row
            Row headerRow = sheet.createRow(0);
            int cellIndex = 0;
            for (java.lang.reflect.Field field : dataList.get(0).getClass().getDeclaredFields()) {
                Cell cell = headerRow.createCell(cellIndex++);
                cell.setCellValue(field.getName());
            }

            // Create data rows
            int rowIndex = 1;
            for (T data : dataList) {
                Row row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                for (java.lang.reflect.Field field : data.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Cell cell = row.createCell(cellIndex++);
                    cell.setCellValue(String.valueOf(field.get(data)));
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            return fileOut;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
