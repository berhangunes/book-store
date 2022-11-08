package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


        User userName(String userName);
        User email(String email);
        User phone(String phone);

        Optional<User> findByUserId(Long userId);
}
