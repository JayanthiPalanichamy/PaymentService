package com.PoC.BrokerSystem.application.port.inbound;

import com.PoC.BrokerSystem.application.domain.payload.Payment;
import com.PoC.BrokerSystem.common.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;

@UseCase
public interface FraudCheckUseCase {
    void convertAndSendToFraudSystem(Payment payment) throws JsonProcessingException;
}
