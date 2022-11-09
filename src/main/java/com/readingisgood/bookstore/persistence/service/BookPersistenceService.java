package com.readingisgood.bookstore.persistence.service;

import com.readingisgood.bookstore.advice.exceptions.BookNotFoundException;
import com.readingisgood.bookstore.converter.BookConverter;
import com.readingisgood.bookstore.persistence.entity.BookEntity;
import com.readingisgood.bookstore.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import request.AddBookRequest;
import response.AddBookDto;
import response.GetBookByIdDto;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookPersistenceService {
    private final BookRepository bookRepository;
    public GetBookByIdDto findBookById(Long bookId){
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        return BookConverter.getBookByIdDto(bookEntity);
    }
    public Integer getStockByName(String name){
        return bookRepository.findByName(name).get().getStock();
    }
    public GetBookByIdDto findByName(String name){
        BookEntity book = bookRepository.findByName(name).orElseThrow(() -> new BookNotFoundException());
        return BookConverter.getBookByIdDto(book);
    }
    public BookEntity decreaseBookStock(Long bookId) {
        BookEntity bookEntity = bookRepository.findBookById(bookId).orElseThrow(()-> new BookNotFoundException());
        int stock = bookEntity.getStock();
        bookEntity.setStock(stock-1);
        return bookRepository.save(bookEntity);
    }
    public BookEntity increaseBookStock(Long bookId){
        BookEntity bookEntity = bookRepository.findBookById(bookId).orElseThrow(()-> new BookNotFoundException());
        int stock = bookEntity.getStock();
        bookEntity.setStock(stock+1);
        return bookRepository.save(bookEntity);
    }
    public Boolean isBookExistsByName(String name){
        return bookRepository.findByName(name).isPresent();
    }
    public Integer getStockById (Long bookId){
        return bookRepository.findBookById(bookId).get().getStock();
    }
    public Double getBookPriceById(Long bookId) {
        return bookRepository.findBookById(bookId).get().getPrice();
    }

    public AddBookDto addBook(AddBookRequest request){
        if (isBookExistsByName(request.getName()) != null && isBookExistsByName(request.getName())) {
            BookEntity bookEntity = bookRepository.findByName(request.getName()).orElseThrow(() -> new BookNotFoundException());
            Integer newStock = getStockByName(request.getName()) + request.getStock();
            bookEntity.setStock(newStock);
            BookEntity updatedBookEntity = bookRepository.save(bookEntity);
            return BookConverter.addBookDto(bookRepository.save(updatedBookEntity));
        } else {
            BookEntity newBookEntity = BookEntity.builder()
                    .name(request.getName())
                    .author(request.getAuthor())
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .build();
            return BookConverter.addBookDto(bookRepository.save(newBookEntity));
        }
    }
    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBookEntities = bookRepository.findAll().stream().collect(Collectors.toList());
        return allBookEntities;
    }
}
