package com.example.codeE.util;

import com.example.codeE.model.exercise.common.IOTestCase;
import com.example.codeE.model.exercise.common.TestCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestcaseUtil {
    public static String convertFormatArray(String arrayString){
        StringBuilder modifiedString = new StringBuilder(arrayString);
        modifiedString.setCharAt(0, '{');
        modifiedString.setCharAt(arrayString.length()-1, '}');
        return modifiedString.toString();
    }

    public static Map<String, String> convertStudentInputsToMap(List<IOTestCase> inputList){
        Map<String, String> result = new HashMap<String, String>();
        int inputQuantity = inputList.size();

        String[] inputs = new String[inputQuantity];
        String[] inputTypes = new String[inputQuantity];

        for(int i = 0; i < inputQuantity; i++){
            IOTestCase input = inputList.get(i);
            inputs[i] = "\"" + input.getValue() + "\"";
            inputTypes[i] = "\"" + input.getDataType() + "\"";
        }

        result.put("inputs", convertFormatArray(Arrays.toString(inputs)));
        result.put("inputTypes", convertFormatArray(Arrays.toString(inputTypes)));

        return result;
    }

    public static Map<String, String> convertTestcaseToMap(TestCase tc){
        Map<String, String> result = new HashMap<String, String>();
        int inputQuantity = tc.getInputs().size();

        String[] parameters = new String[inputQuantity];
        String[] parameterTypes = new String[inputQuantity];
        String expectedOutput = "";
        String expectedOutputType = "";

        for(int i = 0; i < inputQuantity; i++){
            IOTestCase input = tc.getInputs().get(i);
            parameters[i] = "\"" + input.getValue() + "\"";
            parameterTypes[i] = "\"" + input.getDataType() + "\"";
        }

        expectedOutput = "\"" + tc.getOutput().getValue() + "\"";
        expectedOutputType = "\"" + tc.getOutput().getDataType() + "\"";

        result.put("parameters", convertFormatArray(Arrays.toString(parameters)));
        result.put("parameterTypes", convertFormatArray(Arrays.toString(parameterTypes)));
        result.put("expectedOutput", expectedOutput);
        result.put("expectedOutputType", expectedOutputType);

        return result;
    }

    public static Map<String, String> convertTestcaseList(List<TestCase> tcList) {
        Map<String, String> result = new HashMap<String, String>();

        String inputs = "";
        String inputTypes = "";
        String outputs = "";
        String outputTypes = "";

        for(int i = 0; i < tcList.size(); i++){
            Map<String, String> tcString = convertTestcaseToMap(tcList.get(i));
            for (Map.Entry<String,String> entry : tcString.entrySet()){
                switch (entry.getKey()){
                    case "parameters":
                        if (i == tcList.size()-1) {
                            inputs = inputs + entry.getValue();
                        } else {
                            inputs = inputs + entry.getValue() + ", ";
                        }
                        break;
                    case "parameterTypes":
                        if (i == tcList.size()-1) {
                            inputTypes = inputTypes + entry.getValue();
                        } else {
                            inputTypes = inputTypes + entry.getValue() + ", ";
                        }
                        break;
                    case "expectedOutput":
                        if (i == tcList.size()-1) {
                            outputs = outputs + entry.getValue();
                        } else {
                            outputs = outputs + entry.getValue() + ", ";
                        }
                        break;
                    case "expectedOutputType":
                        if (i == tcList.size()-1) {
                            outputTypes = outputTypes + entry.getValue();
                        } else {
                            outputTypes = outputTypes + entry.getValue() + ", ";
                        }
                        break;
                }
            }
        }

        result.put("inputs", inputs);
        result.put("inputTypes", inputTypes);
        result.put("outputs", outputs);
        result.put("outputTypes", outputTypes);

        return result;
    }
}
