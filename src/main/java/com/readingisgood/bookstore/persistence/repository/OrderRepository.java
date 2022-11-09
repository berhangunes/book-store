package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {


    Optional<OrderEntity> findByOrderId(Long orderId);

}
