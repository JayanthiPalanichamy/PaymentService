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
@Constraint(validatedBy = CurrencyValidationImpl.class)
public @interface CurrencyValidation {
    public abstract String message() default "The currency has to be ISO 4217 currency code";
    public abstract Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}



