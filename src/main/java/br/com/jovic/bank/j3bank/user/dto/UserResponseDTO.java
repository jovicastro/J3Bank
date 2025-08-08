package br.com.jovic.bank.j3bank.user.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {}