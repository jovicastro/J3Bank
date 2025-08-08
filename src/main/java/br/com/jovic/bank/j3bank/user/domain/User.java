package br.com.jovic.bank.j3bank.user.domain;

import java.util.List;
import java.util.ArrayList;
import br.com.jovic.bank.j3bank.account.domain.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "User") // Diz ao JPA que esta classe é uma entidade.
@Table(name = "users") // Especifica o nome da tabela no banco (boa prática usar plural).
@Getter // Lombok para gerar getters.
@Setter // Lombok para gerar setters.
@NoArgsConstructor // Lombok para gerar um construtor sem argumentos (exigido pelo JPA).
@EqualsAndHashCode(of = "id") // Lombok para gerar equals() e hashCode() baseados apenas no ID.
public class User {

    @Id // Marca o campo como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para autoincrementar este valor.
    private Long id;

    private String name;

    @Column(unique = true) // Garante que não haverá emails duplicados no banco.
    private String email;

    private String password;

    // Você pode adicionar outros campos aqui, como CPF, data de nascimento, etc.
    // E também construtores, se necessário.
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    @OneToMany(mappedBy = "owner") // "Um Usuário para Muitas Contas"
    private List<Account> accounts = new ArrayList<>();
}