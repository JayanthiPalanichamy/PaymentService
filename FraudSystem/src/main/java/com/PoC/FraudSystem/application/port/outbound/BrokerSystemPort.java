package com.PoC.FraudSystem.application.port.outbound;


public interface BrokerSystemPort {
    void sendToBrokerSystem(String fraudCheckXmlString);
}
