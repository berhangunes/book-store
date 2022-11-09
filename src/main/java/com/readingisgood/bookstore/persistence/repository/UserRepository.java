package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {


        UserEntity userName(String userName);
        UserEntity email(String email);
        UserEntity phone(String phone);

        Optional<UserEntity> findByUserId(Long userId);
}
