package br.com.jovic.bank.j3bank.account.dto;

/**
        * DTO for receiving account creation requests from the client.
  * It contains only the necessary information to create a new account.
  *
    @param accountNumber The desired account number.
    @param agency        The agency number.
    @param ownerId       The ID of the user who will own this account.
  */
public record AccountCreationRequestDTO(
        String accountNumber,
        String agency,
        Long ownerId
) {
}