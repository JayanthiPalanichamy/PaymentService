package com.PoC.BrokerSystem.application.port.inbound;

import com.PoC.BrokerSystem.common.UseCase;

@UseCase
public interface PaymentUseCase {
    void convertAndSendToPaymentSystem(String fraudCheck);
}
