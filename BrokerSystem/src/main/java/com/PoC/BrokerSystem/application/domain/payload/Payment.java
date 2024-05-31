package com.PoC.BrokerSystem.application.domain.payload;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "payment")
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

