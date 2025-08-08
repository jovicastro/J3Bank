package br.com.jovic.bank.j3bank.account.controller;

import br.com.jovic.bank.j3bank.account.dto.AccountResponseDTO;
import br.com.jovic.bank.j3bank.account.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
