package com.example.codeE.validator.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateComparisonValidator.class)
@Documented
public @interface DateComparison {
    String message() default "End time must be greater than start time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?>[] targetClasses() default {};
}
