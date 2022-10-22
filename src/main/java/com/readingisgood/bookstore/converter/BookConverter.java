package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.AddBookDto;
import com.readingisgood.bookstore.model.response.GetAllBooksDto;
import com.readingisgood.bookstore.model.response.GetBookByIdDto;
import com.readingisgood.bookstore.persistence.entity.Book;

public class BookConverter {
    public static GetAllBooksDto getAllBooksDto(Book book){
        return GetAllBooksDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .stock(book.getStock())
                .price(book.getPrice())
                .id(book.getId())
                .build();
    }

    public static GetBookByIdDto getBookByIdDto(Book book){
        return GetBookByIdDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }

    public static AddBookDto addBookDto(Book book){
        return AddBookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }
}
