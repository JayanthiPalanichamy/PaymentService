package com.PoC.PaymentProcessingSystem.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidationImpl.class)
public @interface DateValidation {
    public abstract String format() default "yyyy-MM-dd";
    public abstract String message() default "The date has to be in format";
    public abstract Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}



