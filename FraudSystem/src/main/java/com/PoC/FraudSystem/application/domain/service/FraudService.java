package com.PoC.FraudSystem.application.domain.service;


import com.PoC.FraudSystem.application.domain.payload.FraudCheckResult;
import com.PoC.FraudSystem.application.domain.payload.Payment;
import com.PoC.FraudSystem.application.domain.service.FraudValidation.*;
import com.PoC.FraudSystem.application.port.inbound.FraudCheckUseCase;
import com.PoC.FraudSystem.application.port.outbound.BrokerSystemPort;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.StringReader;
import java.io.StringWriter;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class FraudService implements FraudCheckUseCase {
    private final BrokerSystemPort brokerSystemPort;

    @Override
    public void checkForFraud(String paymentXmlString) {
       try {
           Payment payment = getPaymentFromXml(paymentXmlString);
           FraudCheckResult fraudCheckResult = getFraudValidation().validate(payment);
           String fraudCheckResultXml = convertFraudCheckResultToXml(fraudCheckResult);
           brokerSystemPort.sendToBrokerSystem(fraudCheckResultXml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Payment getPaymentFromXml(String paymentXmlString) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(Payment.class);
            StringReader reader = new StringReader(paymentXmlString);
            return  (Payment) context.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            log.error("Error converting PaymentXml into Payment object {} ", paymentXmlString);
            throw e;
        }
    }

    private String convertFraudCheckResultToXml(FraudCheckResult fraudCheckResult) throws JAXBException {
        try {
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(FraudCheckResult.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(fraudCheckResult, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw e;
        }
    }

    private FraudValidation getFraudValidation() {
        FraudValidation fraudValidation = new NameFraudValidation();
        fraudValidation.setNext(new CountryFraudValidation()).setNext(new BankFraudValidation()).setNext(new PaymentInstructionFraudValidation());
        return fraudValidation;
    }
}
