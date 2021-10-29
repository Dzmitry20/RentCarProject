package com.rentcar.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String login) {
        super(MessageFormat.format("Could not find user with login: {0}", login));
    }
}