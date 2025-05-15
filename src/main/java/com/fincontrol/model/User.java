package com.fincontrol.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private ObjectId poid;
    private String name;
    private String email;
    private String password;
    private Currency currency;

    public User(String name, String email, String password, Currency currency) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.currency = currency;
    }
}
