package br.com.jovic.bank.j3bank.transaction.controller;

import br.com.jovic.bank.j3bank.transaction.dto.TransactionRequestDTO;
import br.com.jovic.bank.j3bank.transaction.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) { this.transactionService = transactionService; }

    @PostMapping
    public ResponseEntity<Void> performTransaction(@RequestBody TransactionRequestDTO requestDTO) {
        transactionService.executeTransaction(requestDTO);
        return ResponseEntity.ok().build();
    }
}

