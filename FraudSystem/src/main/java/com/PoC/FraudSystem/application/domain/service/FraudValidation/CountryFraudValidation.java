package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CountryFraudValidation extends AbstractFraudValidation{
    private Set<String> countries = new HashSet<>(Arrays.asList("CUB", "IRQ", "IRN", "PRK", "SDN", "SYR"));
    @Override
    public FraudCheckResult validate(Payment payment) {
        if(countries.contains(payment.getCountryCode()) || countries.contains(payment.getPayeeCountryCode())) {
            log.error("Fraud found with country in payment {}", payment.getTransactionId());
            return FraudCheckResult.builder().transactionId(payment.getTransactionId()).isFraudDetected(true).message("Suspicious Payment").build();
        }
        log.info("No Fraud found with country in payment {}", payment.getTransactionId());
        return super.validate(payment);
    }
}
