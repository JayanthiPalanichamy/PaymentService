package com.PoC.PaymentProcessingSystem.application.domain.payload;

import com.PoC.PaymentProcessingSystem.common.validator.CountryCodeValidation;
import com.PoC.PaymentProcessingSystem.common.validator.CurrencyValidation;
import com.PoC.PaymentProcessingSystem.common.validator.DateValidation;
import com.PoC.PaymentProcessingSystem.common.validator.DecimalValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class Payment {
    @NotNull(message = "The transaction id has to be unique UUID")
    private UUID transactionId;

    @NotBlank()
    @Size(max = 50, message = "PayerName has be within length 50")
    private String payerName;

    @NotBlank()
    @Size(max = 50, message = "PayerBank has be within length 50")
    private String payerBank;

    @NotBlank()
    @CountryCodeValidation()
    private String countryCode;

    @NotBlank()
    @Size(max = 50, message = "PayeeName has be within length 50")
    private String payeeName;

    @NotBlank()
    @Size(max = 50, message = "PayeeBank has be within length 50")
    private String payeeBank;

    @NotBlank()
    @CountryCodeValidation()
    private String payeeCountryCode;

    @Size(max = 50, message = "paymentInstruction has be within length 50")
    private String paymentInstruction;

    @NotBlank()
    @DateValidation(message = "ExecutionDate has to be ISO 8601 date format")
    private String executionDate;

    @DecimalValidation()
    private double amount;

    @NotBlank(message = "The currency has to be ISO 4217 currency code")
    @CurrencyValidation()
    private String currency;

    @NotBlank()
    @DateValidation(format = "yyyy-MM-dd'T'HH:mm:ss'Z'", message = "CreationTimeStamp has to be ISO 8601 UTC timestamp format")
    private String creationTimeStamp;
}
