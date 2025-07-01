package com.fincontrol.error.user;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse() {
        super("Email is already in use");
    }
}
