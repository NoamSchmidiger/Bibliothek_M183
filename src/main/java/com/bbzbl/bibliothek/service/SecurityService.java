package com.bbzbl.bibliothek.service;

import com.bbzbl.bibliothek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("securityService")
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public boolean isSameUser(Long id, String currentUsername) {
        return userRepository.findById(id)
                .map(user -> user.getUsername().equals(currentUsername))
                .orElse(false);
    }
}
