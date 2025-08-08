package br.com.jovic.bank.j3bank.user.services;

import br.com.jovic.bank.j3bank.user.domain.User;
import br.com.jovic.bank.j3bank.user.dto.UserCreationRequestDTO;
import br.com.jovic.bank.j3bank.user.dto.UserResponseDTO;
import br.com.jovic.bank.j3bank.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDTO createUser(UserCreationRequestDTO requestDTO) {
        User newUser = new User();
        newUser.setName(requestDTO.name());
        newUser.setEmail(requestDTO.email());
        // Em um projeto real, você NUNCA salvaria a senha diretamente.
        // Você usaria um PasswordEncoder do Spring Security para criar um hash.
        // Ex: newUser.setPassword(passwordEncoder.encode(requestDTO.password()));
        newUser.setPassword(requestDTO.password());

        User savedUser = userRepository.save(newUser);

        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }
}