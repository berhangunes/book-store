package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.converter.BookConverter;
import com.readingisgood.bookstore.persistence.service.BookPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.AddBookDto;
import response.GetAllBooksDto;
import response.GetBookByIdDto;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookPersistenceService bookPersistenceService;
    @Cacheable(value = "books")
    public GetBookByIdDto findBookById(Long bookId) {
        return BookConverter.getBookByIdDto(bookPersistenceService.findBookById(bookId));
    }
    @Cacheable(value = "books")
    public List<GetAllBooksDto> getBooks(){
        return bookPersistenceService.getAllBooks().stream().map(BookConverter::getAllBooksDto).collect(Collectors.toList());
    }
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public AddBookDto addBook(String name, String author, Double price, Integer stock) {
        return BookConverter.addBookDto(bookPersistenceService.addBook(name,author,price,stock));
    }


}
