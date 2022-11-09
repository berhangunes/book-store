package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByName(String name);
    Optional<BookEntity> findBookById(Long bookId);
}
