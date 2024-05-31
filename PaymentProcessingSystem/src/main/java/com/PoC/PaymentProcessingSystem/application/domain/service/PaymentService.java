package com.PoC.PaymentProcessingSystem.application.domain.service;

import com.PoC.PaymentProcessingSystem.application.domain.payload.FraudCheckResult;
import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.PaymentProcessingSystem.application.port.inbound.PaymentUseCase;
import com.PoC.PaymentProcessingSystem.application.port.outbound.BrokerPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class PaymentService implements PaymentUseCase, FraudCheckUseCase {
    private final BrokerPort brokerPort;

    public PaymentService(BrokerPort brokerPort) {
        this.brokerPort = brokerPort;
    }

    @Override
    public void processPayment(Payment payment) {
        log.info("Processing Payment with transaction ID: {}", payment.getTransactionId());
        log.info("Fraud check for Payment with transaction ID: {}", payment.getTransactionId());
        brokerPort.sendForFraudCheck(payment);
    }

    @Override
    public void result(FraudCheckResult fraudCheckResult) {
        if(fraudCheckResult.isFraudDetected()) {
            log.error("Fraud is detected with transaction ID: {}", fraudCheckResult.getTransactionId());
            log.info("Cancel Payment for transaction ID : {}", fraudCheckResult.getTransactionId());
            return;
        }
        log.info("No Fraud is detected with transaction ID: {}", fraudCheckResult.getTransactionId());
        log.info("Finish Payment processing for transaction ID: {}", fraudCheckResult.getTransactionId());
    }
}
