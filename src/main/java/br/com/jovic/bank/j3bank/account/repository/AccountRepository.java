package br.com.jovic.bank.j3bank.account.repository;

import br.com.jovic.bank.j3bank.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Spring Data JPA automatically creates the query for this method based on its name.
    // "Find an Account by its accountNumber"
    Optional<Account> findByAccountNumber(String accountNumber);
}
