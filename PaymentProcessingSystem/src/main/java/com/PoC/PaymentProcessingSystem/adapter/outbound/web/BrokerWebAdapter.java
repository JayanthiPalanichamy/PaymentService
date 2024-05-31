package com.PoC.PaymentProcessingSystem.adapter.outbound.web;

import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.application.port.outbound.BrokerPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@ConditionalOnExpression("!${queue.broker.selected}")
public class BrokerWebAdapter implements BrokerPort {
    private final RestTemplate brokerRest;

    public BrokerWebAdapter(RestTemplate brokerRest) {
        this.brokerRest = brokerRest;
    }

    @Override
    public void sendForFraudCheck(Payment payment) {
        log.info("Sending Payment JSON to Broker Service via REST ENDPOINT with transaction ID : {}", payment.getTransactionId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(payment), headers);
            brokerRest.postForObject("/fraud-check", request, String.class);
            log.info("Payment with transaction ID is Successfully sent to Broker Service: {}", payment.getTransactionId());
        } catch (JsonProcessingException e) {
            log.error("Error in converting payment with transaction id {} to json {}", payment.getTransactionId(), e.getMessage());
        } catch (Exception e) {
            log.error("Error in sending payment with transaction id {} to Broker System {}", payment.getTransactionId(), e.getMessage());
        }

    }
}
