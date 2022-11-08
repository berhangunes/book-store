package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.USER_ALREADY_EXISTS;

public class UserAlreadyExistsException extends BookStoreException{
    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS, "User already exists!");
    }
}
