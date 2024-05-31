package com.PoC.BrokerSystem.application.domain.payload;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "fraudCheckResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudCheckResult {
    private UUID transactionId;
    private boolean isFraudDetected;
    private String message;
}
