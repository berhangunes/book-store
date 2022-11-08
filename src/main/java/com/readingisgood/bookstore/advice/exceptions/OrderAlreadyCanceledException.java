package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.ORDER_ALREADY_CANCELED;

public class OrderAlreadyCanceledException extends BookStoreException{
    public OrderAlreadyCanceledException() {
        super(ORDER_ALREADY_CANCELED, "Order already canceled!");
    }
}
