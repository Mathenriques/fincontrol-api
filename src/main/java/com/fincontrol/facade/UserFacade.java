package com.fincontrol.facade;

import com.fincontrol.dto.UserRequestDto;
import com.fincontrol.dto.UserResponseDto;

import java.util.List;

public interface UserFacade {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserDetails();
    List<UserResponseDto> getAllUsers();
    UserResponseDto editUser(UserRequestDto userRequestDto);
}
