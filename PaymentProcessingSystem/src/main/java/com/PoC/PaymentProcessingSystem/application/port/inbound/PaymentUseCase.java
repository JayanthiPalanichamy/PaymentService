package com.PoC.PaymentProcessingSystem.application.port.inbound;

import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.common.UseCase;

@UseCase
public interface PaymentUseCase {
    void processPayment(Payment payment);
}
