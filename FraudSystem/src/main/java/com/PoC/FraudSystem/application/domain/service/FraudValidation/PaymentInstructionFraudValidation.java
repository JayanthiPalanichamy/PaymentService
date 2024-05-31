package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PaymentInstructionFraudValidation extends AbstractFraudValidation{
    private Set<String> paymentInstructions = new HashSet<>(Arrays.asList("Artillery Procurement", "Lethal Chemicals payment"));
    @Override
    public FraudCheckResult validate(Payment payment) {
        if(paymentInstructions.contains(payment.getPaymentInstruction())) {
            log.error("Fraud found with paymeny instruction in payment {}", payment.getTransactionId());
            return FraudCheckResult.builder().transactionId(payment.getTransactionId()).isFraudDetected(true).message("Suspicious Payment").build();
        }
        log.info("No Fraud found with payment instruction in payment {}", payment.getTransactionId());
        return super.validate(payment);
    }
}