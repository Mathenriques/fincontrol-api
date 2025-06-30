package com.fincontrol.controller;

import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.model.User;
import com.fincontrol.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/details")
    public ResponseEntity<UserResponseDto> getUserData() {
        ObjectId poid = (ObjectId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getUserData(poid));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserResponseDto> editUser(@RequestBody User user) {
        ObjectId poid = (ObjectId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPoid(poid);
        return ResponseEntity.ok(userService.editUserData(user));
    }
}
