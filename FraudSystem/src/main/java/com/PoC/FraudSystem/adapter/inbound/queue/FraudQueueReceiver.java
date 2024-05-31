package com.PoC.FraudSystem.adapter.inbound.queue;

import com.PoC.FraudSystem.application.port.inbound.FraudCheckUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@EnableJms
@Slf4j
public class FraudQueueReceiver {
    private final FraudCheckUseCase fraudCheckUseCase;

    public FraudQueueReceiver(FraudCheckUseCase fraudCheckUseCase) {
        this.fraudCheckUseCase = fraudCheckUseCase;
    }


    @JmsListener(destination = "${destination.broker.receiver.name}")
    public void showReceivedMessage(String message) {
        log.info("Message is received from topic subscribed to Fraud system");
        fraudCheckUseCase.checkForFraud(message);
    }

}
