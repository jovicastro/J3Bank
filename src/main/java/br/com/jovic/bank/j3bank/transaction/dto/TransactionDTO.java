package br.com.jovic.bank.j3bank.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(String id, BigDecimal value, LocalDateTime timestamp, String merchantName,
                             String customerId) {
}
