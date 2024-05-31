package com.PoC.PaymentProcessingSystem.application.domain.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class FraudCheckResult {
    @NotNull(message = "The transaction id has to be unique UUID")
    private UUID transactionId;

    @NotNull(message = "The isFraudDetected cannot be null")
    private boolean isFraudDetected;

    @NotBlank()
    @Size(max = 50, message = "message has be within length 50")
    private String message;
}
