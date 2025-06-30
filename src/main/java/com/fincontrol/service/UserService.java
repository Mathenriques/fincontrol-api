package com.fincontrol.service;

import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.model.User;
import com.fincontrol.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        user.setPassword(validateAndHashPassword(user.getPassword()));
        userRepository.save(user);
        return new UserResponseDto(user.getPoid(), user.getName(), user.getEmail(), user.getCurrency());
    }

    public UserResponseDto getUserData(ObjectId poid) {
        User user = userRepository.findByPoid(poid)
                .orElseThrow(() -> {
                    return new RuntimeException("User not found with ID: " + poid);
                });

        return new UserResponseDto(user.getPoid(), user.getName(), user.getEmail(), user.getCurrency());
    }

    public UserResponseDto editUserData(User newUserData) {
        Optional<User> existingUser = userRepository.findByPoid(newUserData.getPoid());
        if(existingUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + newUserData.getPoid());
        }

        User user = existingUser.get();

        Optional<User> userWithSameEmail = userRepository.findByEmail(newUserData.getEmail());
        if(userWithSameEmail.isPresent() && !userWithSameEmail.get().getPoid().equals(newUserData.getPoid())) {
            throw new RuntimeException("Email is already in use");
        }

        user.setPassword(newUserData.getPassword());
        user.setName(newUserData.getName());
        user.setEmail(newUserData.getEmail());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user");
        }

        return new UserResponseDto(user.getPoid(), user.getName(), user.getEmail(), user.getCurrency());

    }

    private String validateAndHashPassword(String rawPassword) {
        String regexPasswordCheck = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        Pattern pattern = Pattern.compile(regexPasswordCheck);

        if (!pattern.matcher(rawPassword).matches() || rawPassword.length() < 8) {
            throw new RuntimeException("Password does not match with rules");
        }

        return passwordEncoder.encode(rawPassword);
    }
}
