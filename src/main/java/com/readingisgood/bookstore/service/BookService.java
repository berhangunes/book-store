package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.converter.BookConverter;
import com.readingisgood.bookstore.model.response.AddBookResponse;
import com.readingisgood.bookstore.model.response.GetAllBooksResponse;
import com.readingisgood.bookstore.model.response.GetBookByIdResponse;
import com.readingisgood.bookstore.persistence.entity.Book;
import com.readingisgood.bookstore.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public GetBookByIdResponse findBookById(Long bookId) {
        Book book = bookRepository.findBookById(bookId);
        return BookConverter.getBookByIdResponse(book);
    }

    public List<GetAllBooksResponse> getBooks(){
        return bookRepository.findAll().stream().map(BookConverter::getAllBooksResponse).collect(Collectors.toList());
    }

    public Integer getStockByName(String name){
        return bookRepository.findByName(name).get().getStock();
    }

    public AddBookResponse addBook(String name, String author, Double price, Integer stock) {

        if (isBookExistsByName(name) != null && isBookExistsByName(name)) {
            Book book = bookRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Book with " + name + " does not exist."));
            Integer newStock = getStockByName(name) + stock;
            book.setStock(newStock);
            Book newBook = bookRepository.save(book);
            return BookConverter.addBookResponse(newBook);

        } else {
            Book book = Book.builder()
                    .name(name)
                    .author(author)
                    .price(price)
                    .stock(stock)
                    .build();
            Book newBook = bookRepository.save(book);
            return BookConverter.addBookResponse(newBook);
        }
    }
    public Double getBookPriceById(Long bookId) {

        return bookRepository.findBookById(bookId).getPrice();

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
}
