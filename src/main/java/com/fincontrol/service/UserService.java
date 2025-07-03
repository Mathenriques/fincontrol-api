package com.fincontrol.service;

import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.error.user.EmailAlreadyInUse;
import com.fincontrol.error.user.FailedToSaveUser;
import com.fincontrol.error.user.PasswordDoesNotMatchWithRules;
import com.fincontrol.error.user.UserNotFound;
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

    public User save(User user) {
        log.info("Creating user");
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyInUse();
        }

        user.setPassword(validateAndHashPassword(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User getUserData(ObjectId poid) {
        return userRepository.findByPoid(poid)
                .orElseThrow(() -> new UserNotFound(poid.toHexString()));
    }

    public User editUserData(User newUserData) {
        Optional<User> existingUser = userRepository.findByPoid(newUserData.getPoid());
        if(existingUser.isEmpty()) {
            throw new UserNotFound(newUserData.getPoid().toHexString());
        }

        User user = existingUser.get();

        Optional<User> userWithSameEmail = userRepository.findByEmail(newUserData.getEmail());
        if(userWithSameEmail.isPresent() && !userWithSameEmail.get().getPoid().equals(newUserData.getPoid())) {
            throw new EmailAlreadyInUse();
        }

        user.setPassword(validateAndHashPassword(newUserData.getPassword()));
        user.setName(newUserData.getName());
        user.setEmail(newUserData.getEmail());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new FailedToSaveUser();
        }

        return user;

    }

    private String validateAndHashPassword(String rawPassword) {
        String regexPasswordCheck = "^(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        Pattern pattern = Pattern.compile(regexPasswordCheck);

        if (!pattern.matcher(rawPassword).matches() || rawPassword.length() < 8) {
            throw new PasswordDoesNotMatchWithRules();
        }

        return passwordEncoder.encode(rawPassword);
    }
}
