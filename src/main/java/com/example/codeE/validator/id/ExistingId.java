package com.example.codeE.validator.id;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistingIdValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingId {
    String message() default "ID does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?>[] targetClasses() default {};
}
