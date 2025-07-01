package com.fincontrol.error.user;

public class FailedToSaveUser extends RuntimeException {
    public FailedToSaveUser() {
        super("Failed to save user");
    }
}
