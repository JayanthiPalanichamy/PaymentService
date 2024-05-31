package com.PoC.PaymentProcessingSystem.adapter.outbound.queue;

import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.application.port.outbound.BrokerPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnExpression("${queue.broker.selected}")
public class BrokerQueueAdapter implements BrokerPort {
    private final JmsTemplate jmsTemplate;

    @Value("${destination.broker.sender.name}")
    private String destinationName;

    public BrokerQueueAdapter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendForFraudCheck(Payment payment) {
        log.info("Sending Payment JSON to Broker Service via Queue with transaction ID : {}", payment.getTransactionId());
        try{
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String paymentXmlString = ow.writeValueAsString(payment);

            jmsTemplate.convertAndSend(this.destinationName, paymentXmlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
