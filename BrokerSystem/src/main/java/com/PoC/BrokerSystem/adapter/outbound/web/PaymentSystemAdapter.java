package com.PoC.BrokerSystem.adapter.outbound.web;

import com.PoC.BrokerSystem.application.domain.payload.FraudCheckResult;
import com.PoC.BrokerSystem.application.port.outbound.PaymentSystemPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@ConditionalOnExpression("!${queue.payment.selected}")
public class PaymentSystemAdapter implements PaymentSystemPort {
    private final RestTemplate restTemplate;

    public PaymentSystemAdapter(RestTemplate brokerRest) {
        this.restTemplate = brokerRest;
    }

    @Override
    public void sendToPaymentSystem(FraudCheckResult fraudCheckResult) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(fraudCheckResult), headers);
            restTemplate.postForObject("/fraud-check", request, String.class);
            log.info("Payment with transaction ID is Successfully sent to Payment Service: {}", fraudCheckResult.getTransactionId());
        } catch (JsonProcessingException e) {
            log.error("Error in Fraud Check Results with transaction id {} to json {}", fraudCheckResult.getTransactionId(), e.getMessage());
        } catch (Exception e) {
            log.error("Error in sending Fraud Check Results with transaction id {} to Payment Processing Service {}", fraudCheckResult.getTransactionId(), e.getMessage());
        }

    }
}
