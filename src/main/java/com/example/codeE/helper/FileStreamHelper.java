package com.example.codeE.helper;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.example.codeE.constant.Constant;
import com.example.codeE.util.DateTimeUtil;

public class FileStreamHelper {
    public static String CreateCodeExerciseFile(String fileCodeContent, String language, String fileName) {
        if (fileCodeContent.isEmpty()) {
            return null;
        }
        var fileExtension = getFileExtension(language);
        var date = DateTimeUtil.getDateNowbyformat(Constant.DATE_TIME_IN_FILE_NAME);
        var filePath = Constant.CODE_EXERCISE_FILE_PATH + language + "/" + fileName + "_" + date
                + fileExtension;
        try (OutputStream output = new FileOutputStream(filePath)) {
            //write content to file
            output.write(fileCodeContent.getBytes());
            return filePath;
        } catch (Exception e) {
            // print log here
            e.printStackTrace();
            return null;
        }
    }
    public static Boolean OverrideFileContent(String filePath, String content) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            //write content to file
            output.write(content.getBytes());
            return true;
        } catch (Exception e) {
            // print log here
            e.printStackTrace();
            return false;
        }

    }

    private static String getFileExtension(String language) {
        switch (language) {
            case "java":
                return ".java";
            case "python":
                return ".py";
            case "csharp":
                return ".cs";
            default:
                return null;
        }
    }
}
