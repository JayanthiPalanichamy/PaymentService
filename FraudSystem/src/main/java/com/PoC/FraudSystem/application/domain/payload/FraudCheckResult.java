package com.PoC.FraudSystem.application.domain.payload;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "fraudCheckResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudCheckResult {
    private UUID transactionId;
    private boolean isFraudDetected;
    private String message;
}
