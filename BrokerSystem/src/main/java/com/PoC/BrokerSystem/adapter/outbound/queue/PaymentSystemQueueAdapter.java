package com.PoC.BrokerSystem.adapter.outbound.queue;

import com.PoC.BrokerSystem.application.domain.payload.FraudCheckResult;
import com.PoC.BrokerSystem.application.port.outbound.PaymentSystemPort;
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
@ConditionalOnExpression("${queue.payment.selected}")
public class PaymentSystemQueueAdapter implements PaymentSystemPort {
    private final JmsTemplate jmsTemplate;

    @Value("${destination.payment.sender.name}")
    private String destinationName;

    public PaymentSystemQueueAdapter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendToPaymentSystem(FraudCheckResult fraudCheckResult) {
        log.info("Send payment xml string to Payment Processing System");
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String fraudCheckXmlString = ow.writeValueAsString(fraudCheckResult);
            jmsTemplate.convertAndSend(this.destinationName, fraudCheckXmlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
