package com.fincontrol.service;

import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.model.User;
import com.fincontrol.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public UserResponseDto save(User user) {
        log.info("Creating user");
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        return new UserResponseDto(user.getPoid(), user.getName(), user.getEmail(), user.getCurrency());
    }

    protected String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
