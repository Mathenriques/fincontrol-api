package com.fincontrol.error.user;

public class PasswordDoesNotMatchWithRules extends RuntimeException {
    public PasswordDoesNotMatchWithRules() {
        super("Password does not match with rules");
    }
}
