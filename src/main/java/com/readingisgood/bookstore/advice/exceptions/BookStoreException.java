package com.readingisgood.bookstore.advice.exceptions;


public class BookStoreException extends RuntimeException{
    private final int code;
    public BookStoreException(int code, String message) {
        super(message);
        this.code = code;
    }
}
