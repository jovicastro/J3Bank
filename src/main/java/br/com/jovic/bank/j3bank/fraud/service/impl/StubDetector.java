package br.com.jovic.bank.j3bank.fraud.service.impl;

import br.com.jovic.bank.j3bank.transaction.dto.TransactionDTO;
import br.com.jovic.bank.j3bank.fraud.dto.DetectionResult;
import br.com.jovic.bank.j3bank.fraud.service.FraudDetector;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("stubDetector")
@Primary
public class StubDetector implements FraudDetector {

    @Override
    public DetectionResult verify(TransactionDTO transaction) {
        System.out.println("WARNING: Using StubDetector. No real fraud detection is active.");
        return new DetectionResult(false, "STUB_IMPLEMENTATION", 0.0);
    }
}
