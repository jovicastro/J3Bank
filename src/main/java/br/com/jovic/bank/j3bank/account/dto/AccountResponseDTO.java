package br.com.jovic.bank.j3bank.account.dto;

import br.com.jovic.bank.j3bank.account.domain.AccountStatus;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        String accountNumber,
        String agency,
        BigDecimal balance,
        AccountStatus status,
        String ownerName
) {}
//zzzzzzzzzzzzzz