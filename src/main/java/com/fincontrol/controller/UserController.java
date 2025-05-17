package com.fincontrol.controller;

import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.model.User;
import com.fincontrol.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fincontrol/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.save(user));
    }
}
