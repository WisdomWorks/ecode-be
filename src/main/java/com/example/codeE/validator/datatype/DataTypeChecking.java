package com.example.codeE.validator.datatype;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataTypeValidator.class)
@Documented
public @interface DataTypeChecking {
    String message() default "Data type is not true";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?>[] targetClasses() default {};
}
