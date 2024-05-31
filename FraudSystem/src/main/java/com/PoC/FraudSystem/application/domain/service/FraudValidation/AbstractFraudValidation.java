package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;

public abstract class AbstractFraudValidation implements FraudValidation {
    private FraudValidation nextFraudValidation;

    public FraudValidation setNext(FraudValidation fraudValidation) {
        this.nextFraudValidation = fraudValidation;
        return fraudValidation;
    }

    public FraudCheckResult validate(Payment payment) {
        if (this.nextFraudValidation != null) {
            return this.nextFraudValidation.validate(payment);
        }
        return FraudCheckResult.builder().transactionId(payment.getTransactionId()).isFraudDetected(false).message("Nothing found, all okay").build();
    }
}
