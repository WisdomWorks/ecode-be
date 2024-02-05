package com.example.codeE.util.excelHelper;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class ExcelHelper {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    public static <T> List<T> importFromExcel(InputStream inputStream, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }
                T instance = clazz.newInstance();

                int cellIndex = 1;
                boolean rowHasData = false;
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Field field = clazz.getDeclaredFields()[cellIndex];
                    field.setAccessible(true);
                    // Set value from Excel cell to corresponding field in the instance
                    if (field.getType() == String.class) {
                        field.set(instance, cell.getStringCellValue());
                    } else if (field.getType() == int.class) {
                        field.set(instance, (int) cell.getNumericCellValue());
                    } else if (field.getType() == double.class) {
                        field.set(instance, cell.getNumericCellValue());
                    } else if (field.getType() == UUID.class) {
                        field.set(instance, UUID.randomUUID().toString());
                    }
                    if (!cell.toString().trim().isEmpty()) {
                        rowHasData = true;
                    }
                    cellIndex++;
                }
                if (rowHasData) {
                    Field idField = clazz.getDeclaredFields()[0];
                    idField.setAccessible(true);
                    idField.set(instance, UUID.randomUUID().toString());

                    list.add(instance);
                }
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return list;
    }
}
