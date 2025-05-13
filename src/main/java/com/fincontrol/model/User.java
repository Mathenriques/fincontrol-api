package com.fincontrol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@AllArgsConstructor()
@Getter
@Setter
public class User {
    @Id
    private Integer poid;
    private String name;
    private String email;
    private String password;
    private Currency currency;
}
