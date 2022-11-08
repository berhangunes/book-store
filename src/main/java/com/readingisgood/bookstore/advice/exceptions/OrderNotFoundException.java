package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.ORDER_NOT_FOUND;

public class OrderNotFoundException extends BookStoreException{
    public OrderNotFoundException() {
        super(ORDER_NOT_FOUND, "Order not found!");
    }
}
