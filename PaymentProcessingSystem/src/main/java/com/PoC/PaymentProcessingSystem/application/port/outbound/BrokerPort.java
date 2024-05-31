package com.PoC.PaymentProcessingSystem.application.port.outbound;

import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;

public interface BrokerPort {
    void sendForFraudCheck(Payment payment);
}
