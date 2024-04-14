package com.example.codeE.helper;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.request.exercise.file.response.ExcelResult;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

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

                    String cellValue = null;
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        default:
                            cellValue = "";
                    }

                    // Set value from Excel cell to corresponding field in the instance
                    if (field.getType() == String.class) {
                        field.set(instance, cellValue);
                    } else if (field.getType() == int.class) {
                        field.set(instance, Integer.parseInt(cellValue));
                    } else if (field.getType() == double.class) {
                        field.set(instance, Double.parseDouble(cellValue));
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
        return !field.getName().equalsIgnoreCase("password");
        //can add more fields to ignore
    }

    public static ExcelResult readQuizQuestionsFromExcel(MultipartFile file) throws IOException {
        List<QuizQuestion> questions = new ArrayList<>();
        List<Integer> failedRows = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                try {
                    String questionTitle = row.getCell(0).getStringCellValue();
                    String questionDescription = ""; // You can add description if needed

                    List<QuizChoice> choices = new ArrayList<>();
                    Set<String> uniqueChoices = new HashSet<>();
                    for (int i = 1; i <= 4; i++) {
                        Cell choiceCell = row.getCell(i);
                        if (choiceCell == null || choiceCell.getCellType() != CellType.STRING || choiceCell.getStringCellValue().isEmpty()) {
                            throw new IllegalArgumentException("All choice columns must be filled.");
                        }
                        String choiceContent = choiceCell.getStringCellValue();
                        if (uniqueChoices.contains(choiceContent)) {
                            throw new IllegalArgumentException("Duplicate choices are not allowed.");
                        }
                        uniqueChoices.add(choiceContent);
                        choices.add(new QuizChoice(choiceContent));
                    }

                    String answerString = row.getCell(5).getStringCellValue();
                    List<QuizChoice> answers = new ArrayList<>();

                    if (answerString.contains(",")) {
                        // Multiple-choice question
                        String[] answerOptions = answerString.split(",");
                        for (String option : answerOptions) {
                            option = option.trim();
                            if (!option.isEmpty() && isValidOption(option)) {
                                answers.add(getChoiceByOption(choices, option));
                            }
                        }
                    } else {
                        // Single-choice question
                        if (isValidOption(answerString)) {
                            answers.add(getChoiceByOption(choices, answerString));
                        }
                    }

                    if (answers.isEmpty()) {
                        throw new IllegalArgumentException("Invalid answer options for the question.");
                    }

                    QuizQuestion question = new QuizQuestion();
                    question.setTitle(questionTitle);
                    question.setDescription(questionDescription);
                    question.setChoices(choices);
                    question.setAnswers(answers);

                    questions.add(question);
                } catch (Exception e) {
                    failedRows.add(row.getRowNum() + 1); // Add 1 to display 1-based index to the user
                }
            }
        }

        return new ExcelResult(questions, failedRows);
    }

    private static boolean isValidOption(String option) {
        return option.equalsIgnoreCase("A") || option.equalsIgnoreCase("B") ||
                option.equalsIgnoreCase("C") || option.equalsIgnoreCase("D");
    }

    private static QuizChoice getChoiceByOption(List<QuizChoice> choices, String option) {
        int index = option.toUpperCase().charAt(0) - 'A';
        if (index >= 0 && index < choices.size()) {
            return choices.get(index);
        }
        throw new IllegalArgumentException("Invalid answer option: " + option);
    }
}