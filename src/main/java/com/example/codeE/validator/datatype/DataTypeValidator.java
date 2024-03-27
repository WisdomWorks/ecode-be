package com.example.codeE.validator.datatype;

import com.example.codeE.model.exercise.DataType;
import com.example.codeE.model.exercise.common.IOTestCase;
import com.example.codeE.model.exercise.common.TestCase;
import com.example.codeE.service.exercise.DataTypeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataTypeValidator implements ConstraintValidator<DataTypeChecking, Object>{
    private final DataTypeService dataTypeService;

    @Autowired
    public DataTypeValidator(DataTypeService dataTypeService){
        this.dataTypeService = dataTypeService;
    }

    @Override
    public void initialize(DataTypeChecking constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return true;
        }
        TestCase testcase = (TestCase) object;
        List<DataType> dataTypeList = dataTypeService.getAllDataTypes();
        for (DataType item : dataTypeList) {
            if (item.getDataTypes().contains(testcase.getOutput().getDataType())) {
                List<IOTestCase> inputList = testcase.getInputs();

                for (IOTestCase input : inputList) {
                    if (!item.getDataTypes().contains(input.getDataType())) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
