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
@Constraint(validatedBy = DecimalValidationImpl.class)
public @interface DecimalValidation {
    public abstract int size() default 2;
    public abstract String message() default "Not enough after decimal point";
    public abstract Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}

