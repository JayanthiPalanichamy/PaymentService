package com.PoC.PaymentProcessingSystem.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;

public class CountryCodeValidationImpl implements ConstraintValidator<CountryCodeValidation, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3).contains(value);
    }
}
