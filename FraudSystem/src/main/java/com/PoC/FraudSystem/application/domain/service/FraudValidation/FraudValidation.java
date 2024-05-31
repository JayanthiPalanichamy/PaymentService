package com.PoC.FraudSystem.application.domain.service.FraudValidation;

import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;

public interface FraudValidation {
    FraudValidation setNext(FraudValidation fraudValidation);
    FraudCheckResult validate(Payment payment);
}

