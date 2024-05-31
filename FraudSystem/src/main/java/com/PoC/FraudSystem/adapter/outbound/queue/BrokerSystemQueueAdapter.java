package com.PoC.FraudSystem.adapter.outbound.queue;

import com.PoC.FraudSystem.application.port.outbound.BrokerSystemPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrokerSystemQueueAdapter implements BrokerSystemPort {
    private final JmsTemplate jmsTemplate;

    @Value("${destination.broker.sender.name}")
    private String destinationName;

    public BrokerSystemQueueAdapter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendToBrokerSystem(String paymentXmlString) {
        log.info("Send payment xml string to Broker System");
        jmsTemplate.convertAndSend(this.destinationName, paymentXmlString);
    }
}
