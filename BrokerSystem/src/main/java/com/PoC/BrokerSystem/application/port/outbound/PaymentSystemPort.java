package com.PoC.BrokerSystem.application.port.outbound;

import com.PoC.BrokerSystem.application.domain.payload.FraudCheckResult;

public interface PaymentSystemPort {
    void sendToPaymentSystem(FraudCheckResult fraudCheckResult);
}
