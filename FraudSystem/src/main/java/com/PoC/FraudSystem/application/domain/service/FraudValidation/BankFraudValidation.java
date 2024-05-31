package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class BankFraudValidation extends AbstractFraudValidation{
    private Set<String> banks = new HashSet<>(Arrays.asList("BANK OF KUNLUN", "KARAMAY CITY COMMERCIAL BANK"));
    @Override
    public FraudCheckResult validate(Payment payment) {
        if(banks.contains(payment.getPayerBank()) || banks.contains(payment.getPayeeBank())) {
            log.error("Fraud found with bank in payment {}", payment.getTransactionId());
            return FraudCheckResult.builder().transactionId(payment.getTransactionId()).isFraudDetected(true).message("Suspicious Payment").build();
        }
        log.info("No Fraud found with bank in payment {}", payment.getTransactionId());
        return super.validate(payment);
    }
}