package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


        User userName(String userName);
        User email(String email);
        User phone(String phone);

        User findByUserId(Long userId);
}
