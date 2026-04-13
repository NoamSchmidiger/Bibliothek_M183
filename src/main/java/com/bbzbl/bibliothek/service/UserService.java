package com.bbzbl.bibliothek.service;

import com.bbzbl.bibliothek.dto.UserDto;
import com.bbzbl.bibliothek.entity.UserEntity;
import com.bbzbl.bibliothek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return mapToDto(entity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserDto createUser(UserDto dto) {
        UserEntity entity = new UserEntity();
        updateEntityFields(entity, dto);

        entity.setPassword(passwordEncoder.encode(dto.password()));

        UserEntity savedEntity = userRepository.save(entity);
        return mapToDto(savedEntity);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        updateEntityFields(entity, dto);

        entity.setPassword(passwordEncoder.encode(dto.password()));

        UserEntity updatedEntity = userRepository.save(entity);
        return mapToDto(updatedEntity);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(UserEntity entity) {
        return new UserDto(
                entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.getUsername(), entity.getPassword(),
                entity.getTelNumber(), entity.getRole()
        );
    }

    private void updateEntityFields(UserEntity entity, UserDto dto) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setUsername(dto.username());
        entity.setTelNumber(dto.telNumber());
        entity.setRole(dto.role());
    }
}
