package com.fincontrol.error.user;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String poid) {
        super(String.format("User not found with ID: %s", poid));
    }
}

