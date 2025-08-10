package br.com.jovic.bank.j3bank.transaction.service;

import br.com.jovic.bank.j3bank.transaction.dto.TransactionDTO;
import br.com.jovic.bank.j3bank.fraud.dto.DetectionResult;
import br.com.jovic.bank.j3bank.fraud.service.FraudDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fraudDetectionTransactionService") // Marca esta classe como um serviço de negócio para o Spring
public class TransactionService {

    private final FraudDetector fraudDetector;

    // Usamos injeção de dependência no construtor.
    // O Spring automaticamente fornecerá a implementação @Primary (nosso StubDetector).
    @Autowired
    public TransactionService(FraudDetector fraudDetector) {
        this.fraudDetector = fraudDetector;
    }

    /**
     * Processes a new transaction, checking it for fraud.
     * @param transaction The transaction to be processed.
     * @return The result of the fraud detection.
     */
    public DetectionResult processTransaction(TransactionDTO transaction) {
        System.out.println("Processing transaction ID: " + transaction.id());

        // A mágica do desacoplamento:
        // Este serviço não sabe e não se importa qual implementação de detector está sendo usada.
        // Ele apenas executa o contrato definido pela interface.
        DetectionResult result = fraudDetector.verify(transaction);

        if (result.isFraud()) {
            // Aqui você poderia adicionar lógica para salvar no banco, notificar, etc.
            System.out.println(
                    "FRAUD DETECTED! Reason: " + result.reason() + ", Score: " + result.score()
            );
        } else {
            System.out.println("Transaction approved. Reason: " + result.reason());
        }

        return result;
    }
}
