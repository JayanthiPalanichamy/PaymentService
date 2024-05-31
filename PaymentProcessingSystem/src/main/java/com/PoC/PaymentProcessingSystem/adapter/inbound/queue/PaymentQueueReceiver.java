package com.PoC.PaymentProcessingSystem.adapter.inbound.queue;

import com.PoC.PaymentProcessingSystem.application.domain.payload.FraudCheckResult;
import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.application.port.inbound.FraudCheckUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@EnableJms
@Slf4j
public class PaymentQueueReceiver {
    private final FraudCheckUseCase fraudCheckUseCase;

    public PaymentQueueReceiver(FraudCheckUseCase fraudCheckUseCase) {
        this.fraudCheckUseCase = fraudCheckUseCase;
    }

    @JmsListener(destination = "${destination.broker.receiver.name}")
    public void receiveMessage(String message) {
        log.info("Message is received from topic subscribed to Broker system");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FraudCheckResult fraudCheckResult = objectMapper.readValue(message, FraudCheckResult.class);
            fraudCheckUseCase.result(fraudCheckResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
