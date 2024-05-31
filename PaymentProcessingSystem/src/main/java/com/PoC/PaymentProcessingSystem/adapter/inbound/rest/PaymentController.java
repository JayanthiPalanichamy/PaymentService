package com.PoC.PaymentProcessingSystem.adapter.inbound.rest;

import com.PoC.PaymentProcessingSystem.application.domain.payload.FraudCheckResult;
import com.PoC.PaymentProcessingSystem.application.domain.payload.Payment;
import com.PoC.PaymentProcessingSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.PaymentProcessingSystem.application.port.inbound.PaymentUseCase;
import com.PoC.PaymentProcessingSystem.common.RestAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestAdapter
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentUseCase paymentUseCase;
    private final FraudCheckUseCase fraudCheckUseCase;

    @PostMapping("/payment")
    void processPayment(@Valid @RequestBody Payment payment) {
        paymentUseCase.processPayment(payment);
    }

    @PostMapping("/fraud-check")
    void resultForFraudCheck(@Valid @RequestBody FraudCheckResult fraudCheckResult) {
        fraudCheckUseCase.result(fraudCheckResult);
    }
}
