package com.example.codeE.validator.date;

import com.example.codeE.model.exercise.Exercise;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateComparisonValidator implements ConstraintValidator<DateComparison, Object> {
    @Override
    public void initialize(DateComparison constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return true;
        }

        if (object instanceof Exercise) {
            Exercise exercise = (Exercise) object;
            return exercise.getEndTime() == null || exercise.getStartTime() == null || exercise.getEndTime().after(exercise.getStartTime());
        }
        return false;
    }
}
