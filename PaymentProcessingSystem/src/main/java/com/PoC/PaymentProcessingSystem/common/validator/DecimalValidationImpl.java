package com.PoC.PaymentProcessingSystem.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class DecimalValidationImpl implements ConstraintValidator<DecimalValidation, Double> {
    private int size;

    @Override
    public void initialize(DecimalValidation constraintAnnotation) {
        this.size = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return BigDecimal.valueOf(value).scale() >= this.size;
    }
}
