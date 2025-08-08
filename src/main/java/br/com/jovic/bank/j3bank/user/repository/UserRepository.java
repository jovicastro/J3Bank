package br.com.jovic.bank.j3bank.user.repository;

import br.com.jovic.bank.j3bank.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // O Spring Data JPA criará automaticamente um método para buscar por e-mail
    // Optional<User> findByEmail(String email);
}
