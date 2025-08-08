package br.com.jovic.bank.j3bank.user.controller;

import br.com.jovic.bank.j3bank.account.dto.AccountResponseDTO;
import br.com.jovic.bank.j3bank.user.dto.UserCreationRequestDTO;
import br.com.jovic.bank.j3bank.user.dto.UserResponseDTO;
import br.com.jovic.bank.j3bank.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        UserResponseDTO userDto = userService.findUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreationRequestDTO requestDTO) {
        UserResponseDTO newUser = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}