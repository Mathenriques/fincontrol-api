package com.fincontrol.controller;

import com.fincontrol.error.user.EmailAlreadyInUse;
import com.fincontrol.error.user.FailedToSaveUser;
import com.fincontrol.error.user.PasswordDoesNotMatchWithRules;
import com.fincontrol.error.user.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FinControlAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyInUse.class)
    public ResponseEntity<String> emailAlreadyInUse(EmailAlreadyInUse error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedToSaveUser.class)
    public ResponseEntity<String> failedToSaveUser(FailedToSaveUser error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordDoesNotMatchWithRules.class)
    public ResponseEntity<String> passwordDoesNotMatchWithRules(PasswordDoesNotMatchWithRules error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> userNotFound(UserNotFound error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
