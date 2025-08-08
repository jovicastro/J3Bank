package br.com.jovic.bank.j3bank.account.services;

import br.com.jovic.bank.j3bank.account.domain.Account;
import br.com.jovic.bank.j3bank.account.repository.AccountRepository;
import br.com.jovic.bank.j3bank.account.domain.AccountStatus;
import br.com.jovic.bank.j3bank.account.dto.AccountCreationRequestDTO;
import br.com.jovic.bank.j3bank.account.dto.AccountResponseDTO;
import br.com.jovic.bank.j3bank.user.domain.User;
import br.com.jovic.bank.j3bank.user.repository.UserRepository;
//import br.com.jovic.bank.j3bank.user.repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository; // Dependency for finding the owner

    // Using constructor injection is a best practice for required dependencies.
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    /**
     // Hidden Lines
     */
    @Transactional(readOnly = true) // Good practice for read operations
    public AccountResponseDTO findAccountById(Long id) {
        Account account = findAccountEntityById(id);
        return convertToDto(account);
    }

/**
 * Creates a new account based on the provided request data.
 * @param requestDTO The DTO containing the creation data.
 * @return An AccountResponseDTO with the newly created account's data.
 */
@Transactional // Read-write transaction
public AccountResponseDTO createAccount(AccountCreationRequestDTO requestDTO) {
    // 1. Find the owner (User)
    User owner = userRepository.findById(requestDTO.ownerId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDTO.ownerId()));

    // 2. Create and configure the new Account entity
    Account newAccount = new Account();
    newAccount.setAccountNumber(requestDTO.accountNumber());
    newAccount.setAgency(requestDTO.agency());
    newAccount.setOwner(owner);
    newAccount.setBalance(java.math.BigDecimal.ZERO); // Business Rule: New accounts start with zero balance
    newAccount.setStatus(AccountStatus.ACTIVE);      // Business Rule: New accounts are created as active

    // 3. Save the new account to the database
    Account savedAccount = accountRepository.save(newAccount);

    // 4. Convert the saved entity to a DTO and return it
    return convertToDto(savedAccount);
}

    // Private helper methods to keep the code clean (DRY - Don't Repeat Yourself)
    private Account findAccountEntityById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    private AccountResponseDTO convertToDto(Account account) {
        // Assuming User has a getName() method
        String ownerName = account.getOwner() != null ? account.getOwner().getName() : null;
        return new AccountResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getAgency(),
                account.getBalance(),
                account.getStatus(),
                ownerName
        );
    }
}