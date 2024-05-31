package com.PoC.BrokerSystem.application.domain.service;

import com.PoC.BrokerSystem.application.domain.payload.FraudCheckResult;
import com.PoC.BrokerSystem.application.domain.payload.Payment;
import com.PoC.BrokerSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.BrokerSystem.application.port.inbound.PaymentUseCase;
import com.PoC.BrokerSystem.application.port.outbound.FraudSystemPort;
import com.PoC.BrokerSystem.application.port.outbound.PaymentSystemPort;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringReader;
import java.io.StringWriter;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class BrokerService implements FraudCheckUseCase, PaymentUseCase {
    private final FraudSystemPort fraudSystemPort;
    private final PaymentSystemPort paymentSystemPort;

    @Override
    public void convertAndSendToFraudSystem(Payment payment) {
        log.info("Convert payment string into xml string");
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(Payment.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(payment, stringWriter);
            String paymentXmlString = stringWriter.toString();
            fraudSystemPort.sendForFraudSystem(paymentXmlString);
        }  catch (JAXBException e) {
            log.error("Error Converting Payment into xml string {}", e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void convertAndSendToPaymentSystem(String fraudCheckXmlString) {
        log.info("Convert Fraud Check xml string to Object");
        try {
            JAXBContext context = JAXBContext.newInstance(FraudCheckResult.class);
            StringReader reader = new StringReader(fraudCheckXmlString);
            FraudCheckResult fraudCheckResult = (FraudCheckResult) context.createUnmarshaller().unmarshal(reader);
            log.info("Send FraudCheck to Payment System");
            paymentSystemPort.sendToPaymentSystem(fraudCheckResult);
        } catch (JAXBException e) {
            log.error("Error Converting Fraud Check xml string to Object {}", e.getMessage());
            e.printStackTrace();
        }

    }
}
