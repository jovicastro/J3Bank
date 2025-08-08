package br.com.jovic.bank.j3bank.transaction.repository;

import br.com.jovic.bank.j3bank.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
