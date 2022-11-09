package com.readingisgood.bookstore.persistence.service;

import com.readingisgood.bookstore.persistence.entity.BookEntity;
import com.readingisgood.bookstore.persistence.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import request.AddBookRequest;
import response.AddBookDto;
import response.GetBookByIdDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookEntityPersistenceServiceTest {

    @InjectMocks
    private BookPersistenceService bookPersistenceService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void addBookTest(){
        String name = "Book name";
        String author = "Author name";
        Double price = 10.0;
        Integer stock = 2;
        AddBookRequest addBookRequest = AddBookRequest.builder()
                .name(name)
                .author(author)
                .price(price)
                .stock(stock)
                .build();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(name);
        bookEntity.setAuthor(author);
        bookEntity.setPrice(price);
        bookEntity.setStock(stock);
        given(bookRepository.save(any(BookEntity.class)))
                .willReturn(bookEntity);
        AddBookDto actual = bookPersistenceService.addBook(addBookRequest);
        then(bookRepository).should(times(1)).save(any(BookEntity.class));
        assertThat(actual.getId()).isEqualTo(bookEntity.getId());
        assertThat(actual.getAuthor()).isEqualTo(bookEntity.getAuthor());
        assertThat(actual.getName()).isEqualTo(bookEntity.getName());
        assertThat(actual.getPrice()).isEqualTo(bookEntity.getPrice());
        assertThat(actual.getStock()).isEqualTo(bookEntity.getStock());
    }
    @Test
    void findBookByIdTest(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(28L);
        GetBookByIdDto actual = bookPersistenceService.findBookById(28L);
        when(bookRepository.findBookById(28L)).thenReturn(Optional.of(bookEntity));
        assertThat(actual.getId()).isEqualTo(bookEntity.getId());

    }
}