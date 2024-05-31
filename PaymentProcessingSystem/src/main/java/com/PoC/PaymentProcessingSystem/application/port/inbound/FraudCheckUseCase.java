package com.PoC.PaymentProcessingSystem.application.port.inbound;

import com.PoC.PaymentProcessingSystem.application.domain.payload.FraudCheckResult;
import com.PoC.PaymentProcessingSystem.common.UseCase;

@UseCase
public interface FraudCheckUseCase {
    void result(FraudCheckResult fraudCheckResult);
}
