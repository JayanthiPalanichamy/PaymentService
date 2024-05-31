package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class NameFraudValidation extends AbstractFraudValidation{
    private Set<String> names = new HashSet<>(Arrays.asList("Mark Imaginary", "Govind Real", "Shakil Maybe", "Chang Imagine"));
    @Override
    public FraudCheckResult validate(Payment payment) {
        if(names.contains(payment.getPayerName()) || names.contains(payment.getPayeeName())) {
            log.error("Fraud found with name in payment {}", payment.getTransactionId());
            return FraudCheckResult.builder().transactionId(payment.getTransactionId()).isFraudDetected(true).message("Suspicious Payment").build();
        }
        log.info("No Fraud found with name in payment {}", payment.getTransactionId());
        return super.validate(payment);
    }
}
