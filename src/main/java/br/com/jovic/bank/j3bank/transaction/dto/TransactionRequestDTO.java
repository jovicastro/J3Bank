package br.com.jovic.bank.j3bank.transaction.dto;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        Long sourceAccountId,
        Long destinationAccountId,
        BigDecimal amount
) {
}
