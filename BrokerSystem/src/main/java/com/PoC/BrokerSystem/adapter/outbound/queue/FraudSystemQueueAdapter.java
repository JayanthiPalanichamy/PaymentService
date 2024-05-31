package com.PoC.BrokerSystem.adapter.outbound.queue;

import com.PoC.BrokerSystem.application.port.outbound.FraudSystemPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FraudSystemQueueAdapter implements FraudSystemPort {
    private final JmsTemplate jmsTemplate;

    @Value("${destination.fraud.sender.name}")
    private String destinationName;

    public FraudSystemQueueAdapter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendForFraudSystem(String paymentXmlString) {
        log.info("Send Payment xml string to Fraud System");
        jmsTemplate.convertAndSend(this.destinationName, paymentXmlString);
    }
}
