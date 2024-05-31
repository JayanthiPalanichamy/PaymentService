package com.PoC.BrokerSystem.adapter.inbound.rest;

import com.PoC.BrokerSystem.application.domain.payload.Payment;
import com.PoC.BrokerSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.BrokerSystem.common.RestAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestAdapter
@RequiredArgsConstructor
@Slf4j
public class BrokerController {
    private final FraudCheckUseCase fraudCheckUseCase;

    @PostMapping("/fraud-check")
    void fraudCheck(@RequestBody Payment payment) {
        log.info("Received Payment via REST endpoint with transaction ID: {}", payment.getTransactionId());
        try {
            log.info("Received call from payment processing service to convert and send to Fraud Service {}", payment.getTransactionId());
            fraudCheckUseCase.convertAndSendToFraudSystem(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
