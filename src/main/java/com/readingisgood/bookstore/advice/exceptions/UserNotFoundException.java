package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.USER_NOT_FOUND;

public class UserNotFoundException extends BookStoreException{
    public UserNotFoundException() {
        super(USER_NOT_FOUND, "User not found!");
    }
}
