package com.fincontrol.service;

import com.fincontrol.model.User;
import com.fincontrol.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private  UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        log.info("Creating user");
        return userRepository.save(user);
    }
}
