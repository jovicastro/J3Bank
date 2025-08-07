package br.com.jovic.bank.j3bank.fraud.service;

import br.com.jovic.bank.j3bank.transaction.dto.TransactionDTO;
import br.com.jovic.bank.j3bank.fraud.dto.DetectionResult;

public interface FraudDetector {
    /**
     * Contract for all fraud detectors.
     * Receives a transaction and returns the analysis result.
     */
    DetectionResult verify(TransactionDTO transaction);
}