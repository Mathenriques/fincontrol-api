package com.fincontrol.facade;

import com.fincontrol.dto.UserRequestDto;
import com.fincontrol.dto.UserResponseDto;
import com.fincontrol.model.User;
import com.fincontrol.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacade {

    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getCurrency());

        User savedUser = userService.save(user);

        return new UserResponseDto(savedUser.getPoid(), savedUser.getName(), savedUser.getEmail(), savedUser.getCurrency());
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> usersList = userService.getAll();

        return usersList.stream()
                .map(user -> new UserResponseDto(user.getPoid(), user.getName(), user.getEmail(), user.getCurrency()))
                .toList();
    }

    public UserResponseDto editUser(UserRequestDto userRequestDto) {
        ObjectId poid = (ObjectId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getCurrency());

        user.setPoid(poid);

        User userUpdate = userService.editUserData(user);

        return new UserResponseDto(userUpdate.getPoid(), userUpdate.getName(), userUpdate.getEmail(), userUpdate.getCurrency());
    }
}
