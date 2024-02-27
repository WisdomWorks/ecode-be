package com.example.codeE.validator.id;

import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.model.request.exercise.DeleteExerciseRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingIdValidator implements ConstraintValidator<ExistingId, Object> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public void initialize(ExistingId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object instanceof DeleteExerciseRequest) {
            DeleteExerciseRequest deleteExerciseRequest = (DeleteExerciseRequest) object;
            return exerciseRepository.existsById(deleteExerciseRequest.getExerciseId());
        }
        return false;
    }
}

