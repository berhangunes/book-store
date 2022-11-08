package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.NOT_ENOUGH_STOCK;

public class NotEnoughStockException extends BookStoreException{
    public NotEnoughStockException() {
        super(NOT_ENOUGH_STOCK, "Not enough stock!");
    }
}
