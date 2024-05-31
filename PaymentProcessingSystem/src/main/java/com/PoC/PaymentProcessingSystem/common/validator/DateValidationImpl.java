package com.PoC.PaymentProcessingSystem.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidationImpl implements ConstraintValidator<DateValidation, String> {
    private String format;

    @Override
    public void initialize(DateValidation constraintAnnotation) {
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DateFormat sdf = new SimpleDateFormat(this.format);
        sdf.setLenient(false);
        try {
            sdf.parse(value.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
