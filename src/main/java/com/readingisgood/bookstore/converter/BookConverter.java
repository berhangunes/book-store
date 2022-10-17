package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.AddBookResponse;
import com.readingisgood.bookstore.model.response.GetAllBooksResponse;
import com.readingisgood.bookstore.model.response.GetBookByIdResponse;
import com.readingisgood.bookstore.persistence.entity.Book;

public class BookConverter {
    public static GetAllBooksResponse getAllBooksResponse(Book book){
        return GetAllBooksResponse.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .stock(book.getStock())
                .price(book.getPrice())
                .id(book.getId())
                .build();
    }

    public static GetBookByIdResponse getBookByIdResponse(Book book){
        return GetBookByIdResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }

    public static AddBookResponse addBookResponse(Book book){
        return AddBookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }
}
