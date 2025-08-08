package br.com.jovic.bank.j3bank.user.dto;

public record UserCreationRequestDTO(
        String name,
        String email,
        String password
) {
}