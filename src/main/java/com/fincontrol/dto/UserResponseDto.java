package com.fincontrol.dto;

import com.fincontrol.model.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class UserResponseDto {
    private String poid;
    private String name;
    private String email;
    private Currency currency;

    public UserResponseDto(ObjectId poid, String name, String email, Currency currency) {
        this.poid = poid.toHexString();
        this.name = name;
        this.email = email;
        this.currency = currency;
    }
}
