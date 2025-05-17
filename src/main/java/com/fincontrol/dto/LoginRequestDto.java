package com.fincontrol.dto;

import lombok.Getter;

public record LoginRequestDto(String email, String password) {
}
