package com.PoC.BrokerSystem.adapter.inbound.queue;

import com.PoC.BrokerSystem.application.domain.payload.Payment;
import com.PoC.BrokerSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.BrokerSystem.application.port.inbound.PaymentUseCase;
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
public class BrokerQueueReceiver {
    private final PaymentUseCase paymentUseCase;
    private final FraudCheckUseCase fraudCheckUseCase;

    public BrokerQueueReceiver(PaymentUseCase paymentUseCase, FraudCheckUseCase fraudCheckUseCase) {
        this.paymentUseCase = paymentUseCase;
        this.fraudCheckUseCase = fraudCheckUseCase;
    }


    @JmsListener(destination = "${destination.fraud.receiver.name}")
    public void receiveMessageFromFraudSystem(String message) {
        log.info("Message is received from topic subscribed to Fraud system");
        paymentUseCase.convertAndSendToPaymentSystem(message);
    }

    @JmsListener(destination = "${destination.payment.receiver.name}")
    public void receiveMessageFromPaymentSystem(String message) {
        log.info("Message is received from topic subscribed to Payment system");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Payment payment = objectMapper.readValue(message, Payment.class);
            log.info("Received Payment via Queue with transaction ID: {}", payment.getTransactionId());
            log.info("Received call from payment processing service to convert and send to Fraud Service {}", payment.getTransactionId());
            fraudCheckUseCase.convertAndSendToFraudSystem(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
