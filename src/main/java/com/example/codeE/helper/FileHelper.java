package com.example.codeE.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileHelper {
    public static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static String replaceTemplateFile(String filePath,
                                             String methodName,
                                             String isCodeRunning,
                                             Map<String, String> testcases,
                                             Map<String, String> enteredInputs) {
        String contentFile = readFileToString(filePath);

        String inputs = "{}";
        String inputTypes = "{}";
        String outputs = "{}";
        String outputTypes = "{}";

        for (Map.Entry<String,String> entry : testcases.entrySet()){
            switch (entry.getKey()){
                case "inputs":
                    inputs = entry.getValue();
                    break;
                case "inputTypes":
                    inputTypes = entry.getValue();
                    break;
                case "outputs":
                    outputs = entry.getValue();
                    break;
                case "outputTypes":
                    outputTypes = entry.getValue();
                    break;
            }
        }

        String enteredInput = "{}";
        String enteredInputType = "{}";
        for (Map.Entry<String,String> entry : enteredInputs.entrySet()){
            switch (entry.getKey()){
                case "inputs":
                    enteredInput = entry.getValue();
                    break;
                case "inputTypes":
                    enteredInputType = entry.getValue();
                    break;
            }
        }

        return String.format(
                contentFile,
                methodName,
                isCodeRunning,
                inputs,
                inputTypes,
                outputs,
                outputTypes,
                enteredInput,
                enteredInputType);
    }
}
