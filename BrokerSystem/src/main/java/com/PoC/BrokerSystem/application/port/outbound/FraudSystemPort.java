package com.PoC.BrokerSystem.application.port.outbound;


public interface FraudSystemPort {
    void sendForFraudSystem(String paymentXmlString);
}
