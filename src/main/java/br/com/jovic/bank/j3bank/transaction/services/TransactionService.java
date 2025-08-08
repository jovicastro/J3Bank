package br.com.jovic.bank.j3bank.transaction.services;

import br.com.jovic.bank.j3bank.account.domain.Account;
import br.com.jovic.bank.j3bank.account.domain.AccountStatus;
import br.com.jovic.bank.j3bank.account.repository.AccountRepository;
import br.com.jovic.bank.j3bank.transaction.domain.Transaction;
import br.com.jovic.bank.j3bank.transaction.dto.TransactionRequestDTO;
import br.com.jovic.bank.j3bank.transaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Executes a financial transaction between two accounts.
     * This operation is transactional. If any step fails, the entire operation is rolled back.
     */
    @Transactional
    public void executeTransaction(TransactionRequestDTO requestDTO) {
        // 1. Validate input
        if (requestDTO.sourceAccountId().equals(requestDTO.destinationAccountId())) {
            throw new IllegalArgumentException("Source and destination accounts cannot be the same.");
        }
        if (requestDTO.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive.");
        }

        // 2. Find accounts
        Account sourceAccount = accountRepository.findById(requestDTO.sourceAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Source account not found."));
        Account destinationAccount = accountRepository.findById(requestDTO.destinationAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Destination account not found."));

        // 3. Check business rules
        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Source account is not active.");
        }
        if (sourceAccount.getBalance().compareTo(requestDTO.amount()) < 0) {
            throw new IllegalStateException("Insufficient funds in the source account.");
        }

        // 4. Perform the transfer
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(requestDTO.amount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(requestDTO.amount()));

        // 5. Record the transaction
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(requestDTO.amount());
        transactionRepository.save(transaction);
    }
}