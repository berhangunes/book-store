package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
    Book findBookById(Long bookId);
}
