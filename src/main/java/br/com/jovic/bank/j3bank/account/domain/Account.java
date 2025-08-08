package br.com.jovic.bank.j3bank.account.domain;

import br.com.jovic.bank.j3bank.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Setter
@Getter
@Entity // define como uma entidade e cria uma tabela no banco
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//GerType delega ao banco a grcao do ID
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String accountNumber;

    @Column(nullable = false, length = 4)
    private String agency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne // "Muitas Contas para Um Usuário"
    @JoinColumn(name = "user_id", nullable = false) // A chave estrangeira na tabela 'accounts'
    private User owner;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
}

