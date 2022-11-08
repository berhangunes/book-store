package com.readingisgood.bookstore.advice.exceptions;

import static com.readingisgood.bookstore.advice.constants.ErrorCodes.BOOK_NOT_FOUND;
public class BookNotFoundException extends BookStoreException {
        public BookNotFoundException() {
            super(BOOK_NOT_FOUND,"Book not found!");
        }
}

