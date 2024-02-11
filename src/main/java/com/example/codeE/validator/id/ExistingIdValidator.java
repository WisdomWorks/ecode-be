package com.example.codeE.validator.id;

import com.example.codeE.repository.MSExerciseRepository;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistingIdValidator implements ConstraintValidator<ExistingId, Object> {

    @Autowired
    private MSExerciseRepository msExerciseRepository;

    @Override
    public void initialize(ExistingId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object instanceof DeleteExerciseRequest) {
            DeleteExerciseRequest deleteExerciseRequest = (DeleteExerciseRequest) object;
            return msExerciseRepository.existsById(deleteExerciseRequest.getExerciseId());
        }
        return false;
    }
}

