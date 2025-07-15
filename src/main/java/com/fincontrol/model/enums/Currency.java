package com.fincontrol.model.enums;

import lombok.Getter;

@Getter
public enum Currency {
    DOLLAR("Dollar"),
    REAL("Real");

    private final String name;

    Currency(String name) {
        this.name = name;
    }
}
