package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.persistence.entity.BookEntity;
import response.AddBookDto;
import response.GetAllBooksDto;
import response.GetBookByIdDto;

public class BookConverter {
    public static GetAllBooksDto getAllBooksDto(BookEntity bookEntity){
        return GetAllBooksDto.builder()
                .name(bookEntity.getName())
                .author(bookEntity.getAuthor())
                .stock(bookEntity.getStock())
                .price(bookEntity.getPrice())
                .id(bookEntity.getId())
                .build();
    }
    public static GetBookByIdDto getBookByIdDto(BookEntity bookEntity){
        return GetBookByIdDto.builder()
                .id(bookEntity.getId())
                .name(bookEntity.getName())
                .author(bookEntity.getAuthor())
                .price(bookEntity.getPrice())
                .stock(bookEntity.getStock())
                .build();
    }
    public static AddBookDto addBookDto(BookEntity bookEntity){
        return AddBookDto.builder()
                .id(bookEntity.getId())
                .name(bookEntity.getName())
                .author(bookEntity.getAuthor())
                .price(bookEntity.getPrice())
                .stock(bookEntity.getStock())
                .build();
    }
}
