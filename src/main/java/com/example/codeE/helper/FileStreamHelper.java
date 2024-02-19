package com.example.codeE.helper;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import com.example.codeE.constant.Constant;

public class FileStreamHelper {
    public static String CreateCodeExerciseFile(String fileCodeContent, String language, String fileName) {
        if (fileCodeContent.isEmpty()) {
            return null;
        }
        var fileExtension = getFileExtension(language);
        var filePath = Constant.CODE_EXERCISE_FILE_PATH + fileName + "_" + LocalDate.now() + fileExtension;
        try (OutputStream output = new FileOutputStream(filePath)){
            return filePath;
        } catch (Exception e) {
            //print log here
            e.printStackTrace();
            return null;
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
