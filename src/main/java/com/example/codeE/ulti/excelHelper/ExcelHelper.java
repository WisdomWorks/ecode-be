package com.example.codeE.ulti.excelhelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
     public static FileOutputStream  writeExcel(Workbook workbook, List<T> dataList, String filePath, String sheetName){
        try {
            Sheet sheet = workbook.createSheet(sheetName);        
            Row headerRow = sheet.createRow(0);
            int cellIndex = 0;
            for (java.lang.reflect.Field field : dataList.get(0).getClass().getDeclaredFields()) {
                Cell cell = headerRow.createCell(cellIndex++);
                cell.setCellValue(field.getName());
            }

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
    public static List<T> importFromExcel(String filePath, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                T object = clazz.newInstance();
                int cellIndex = 0;
                for (Cell cell : row) {
                    Field field = clazz.getDeclaredFields()[cellIndex];
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(object, cell.getStringCellValue());
                    } else if (field.getType() == int.class) {
                        field.set(object, (int) cell.getNumericCellValue());
                    } else if (field.getType() == double.class) {
                        field.set(object, cell.getNumericCellValue());
                    } else if (field.getType() == UUID.class) {
                        field.set(object, UUID.fromString(cell.getStringCellValue()));
                    } else {
                        field.set(object, null);
                    }

                    cellIndex++;
                }

                dataList.add(object);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
