package com.fincontrol.dto;

import com.fincontrol.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor()
@Getter
public class UserRequestDto {
    private String poid;
    private String name;
    private String password;
    private String email;
    private Currency currency;
}
