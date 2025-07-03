package com.fincontrol.controller;

import com.fincontrol.dto.UserRequestDto;
import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.facade.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fincontrol/users")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userFacade.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        return ResponseEntity.status(201).body(userFacade.createUser(user));
    }

    @GetMapping("/details")
    public ResponseEntity<UserResponseDto> getUserData() {
        return ResponseEntity.ok(userFacade.getUserDetails());
    }

    @PutMapping("/edit")
    public ResponseEntity<UserResponseDto> editUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userFacade.editUser(user));
    }
}
