package br.com.jovic.bank.j3bank.account.controller;

import br.com.jovic.bank.j3bank.account.dto.AccountCreationRequestDTO;
import br.com.jovic.bank.j3bank.account.dto.AccountResponseDTO;
import br.com.jovic.bank.j3bank.account.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    // Constructor injection is a Spring best practice
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findAccountById(@PathVariable Long id) {
        AccountResponseDTO accountDto = accountService.findAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountCreationRequestDTO requestDTO) {
        // 1. Chama o serviço para executar a lógica de criação da conta
        AccountResponseDTO newAccount = accountService.createAccount(requestDTO);

        // 2. Retorna uma resposta HTTP 201 (Created) com os dados da nova conta no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }
}
