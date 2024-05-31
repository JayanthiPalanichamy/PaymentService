package com.PoC.FraudSystem.application.domain.payload;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    private UUID transactionId;
    private String payerName;
    private String payerBank;
    private String countryCode;
    private String payeeName;
    private String payeeBank;
    private String payeeCountryCode;
    private String paymentInstruction;
    private String executionDate;
    private double amount;
    private String currency;
    private String creationTimeStamp;
}
