package com.example.codeE.validator.datatype;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.DataType;
import com.example.codeE.model.exercise.common.IOTestCase;
import com.example.codeE.service.exercise.DataTypeImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataTypeValidator implements ConstraintValidator<DataTypeChecking, Object>{
    private final DataTypeImpl dataTypeImpl;

    @Autowired
    public DataTypeValidator(DataTypeImpl dataTypeImpl){
        this.dataTypeImpl = dataTypeImpl;
    }

    @Override
    public void initialize(DataTypeChecking constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return true;
        }

        if (object instanceof CodeExercise) {
            CodeExercise codeExercise = (CodeExercise) object;
            List<DataType> dataTypeList = dataTypeImpl.getAllDataTypes();
            for (DataType item : dataTypeList) {
                if (item.getName().equals(codeExercise.getLanguage())) {
                    if (item.getDataTypes().contains(codeExercise.getTestcase().getOutput().getDataType())) {
                        List<IOTestCase> inputList = codeExercise.getTestcase().getInputs();

                        for (IOTestCase input : inputList) {
                            if (!item.getDataTypes().contains(input.getDataType())) {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
