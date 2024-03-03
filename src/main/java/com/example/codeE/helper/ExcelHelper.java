package com.example.codeE.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.codeE.constant.Constant;

public class ExcelHelper {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static <T> String writeExcel(XSSFWorkbook workbook, List<T> dataList, String fileName, String sheetName) {
        try {
            Sheet sheet = workbook.createSheet(sheetName);
            Row headerRow = sheet.createRow(0);
            int cellIndex = 0;
            for (Field field : dataList.get(0).getClass().getDeclaredFields()) {
                if (isDisplayColumn(field)) {
                    Cell cell = headerRow.createCell(cellIndex++);
                    cell.setCellValue(field.getName());
                }
            }
            int rowIndex = 1;
            for (Object data : dataList) {
                Row row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                for (Field field : data.getClass().getDeclaredFields()) {
                    if (isDisplayColumn(field)) {
                        field.setAccessible(true);
                        Cell cell = row.createCell(cellIndex++);
                        cell.setCellValue(String.valueOf(field.get(data)));
                    }
                }
            }

            FileOutputStream fileOut = new FileOutputStream(Constant.EXCEL_FILE_PATH + fileName);
            workbook.write(fileOut);
            fileOut.close();
            return Constant.EXCEL_FILE_PATH + fileName;
        } catch (Exception e) {
            // need to log this exception
            // remember delete file after upload into s3
            e.printStackTrace();
            return null;
        }
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

    private static Boolean isDisplayColumn(Field field) {
        if (field.getName().equalsIgnoreCase("password")) {
            return false;
        }
        //can add more fields to ignore
        return true;
    }

    public static <T> List<T> getAllFieldsFromExcel(InputStream inputStream, Class<T> clazz) {
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

                int cellIndex = 0;
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
                    list.add(instance);
                }
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return list;
    }
}
