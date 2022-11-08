package com.readingisgood.bookstore.persistence.service;

import com.readingisgood.bookstore.advice.exceptions.BookNotFoundException;
import com.readingisgood.bookstore.persistence.entity.Book;
import com.readingisgood.bookstore.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookPersistenceService {
    private final BookRepository bookRepository;
    public Book findBookById(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        return book;
    }
    public Integer getStockByName(String name){
        return bookRepository.findByName(name).get().getStock();
    }
    public Book findByName(String name){
        return bookRepository.findByName(name).orElseThrow(() -> new BookNotFoundException());
    }
    public Book decreaseBookStock(Long bookId) {
        Book book = bookRepository.findBookById(bookId);
        int stock = book.getStock();
        book.setStock(stock-1);
        return bookRepository.save(book);
    }
    public Book increaseBookStock(Long bookId){
        Book book = bookRepository.findBookById(bookId);
        int stock = book.getStock();
        book.setStock(stock+1);
        return bookRepository.save(book);
    }
    public Boolean isBookExistsByName(String name){
        return bookRepository.findByName(name).isPresent();
    }
    public Integer getStockById (Long bookId){
        return bookRepository.findBookById(bookId).getStock();
    }
    public Double getBookPriceById(Long bookId) {
        return bookRepository.findBookById(bookId).getPrice();
    }

    public Book addBook(String name, String author, Double price, Integer stock){
        if (isBookExistsByName(name) != null && isBookExistsByName(name)) {
            Book book = findByName(name);
            Integer newStock = getStockByName(name) + stock;
            book.setStock(newStock);
            Book updatedBook = bookRepository.save(book);
            return bookRepository.save(updatedBook);
        } else {
            Book newBook = Book.builder()
                    .name(name)
                    .author(author)
                    .price(price)
                    .stock(stock)
                    .build();
            return bookRepository.save(newBook);
        }
    }
    public List<Book> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll().stream().collect(Collectors.toList());
        return allBooks;
    }
}
