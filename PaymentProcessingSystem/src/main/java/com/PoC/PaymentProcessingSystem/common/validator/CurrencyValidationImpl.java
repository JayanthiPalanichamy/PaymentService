package com.PoC.PaymentProcessingSystem.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;
import java.util.Objects;
import java.util.Set;

public class CurrencyValidationImpl implements ConstraintValidator<CurrencyValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        for (Currency currency : currencies) {
            if (Objects.equals(currency.getCurrencyCode(), value)) {
                return true;
            }
        }
        return false;
    }
}
