package br.com.jovic.bank.j3bank.fraud.dto;

// Use a palavra-chave 'record'
public record DetectionResult(
        boolean isFraud,
        String reason,
        double score
) {}