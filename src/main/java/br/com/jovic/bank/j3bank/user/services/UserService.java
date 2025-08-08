package br.com.jovic.bank.j3bank.user.services;

import br.com.jovic.bank.j3bank.user.domain.User;
import br.com.jovic.bank.j3bank.user.dto.UserCreationRequestDTO;
import br.com.jovic.bank.j3bank.user.dto.UserResponseDTO;
import br.com.jovic.bank.j3bank.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

     //Finds a user by their ID and returns it as a DTO.
     //@param id The ID of the user to find.
     //@return A UserResponseDTO with the user's public data.
     //@throws EntityNotFoundException if no user is found with the given ID.

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id) {
        User user = findUserEntityById(id);
        return convertToDto(user);
    }
    private User findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    private UserResponseDTO convertToDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

}