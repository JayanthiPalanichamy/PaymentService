package com.PoC.FraudSystem.application.port.inbound;

import com.PoC.FraudSystem.common.UseCase;

@UseCase
public interface FraudCheckUseCase {
    void checkForFraud(String paymentXmlString);
}
