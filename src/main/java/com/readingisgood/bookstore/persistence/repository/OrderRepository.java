package com.readingisgood.bookstore.persistence.repository;

import com.readingisgood.bookstore.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByCustomerId(Long customerId);

    List <Order> findOrdersByCustomerId(Long customerId);

    Order findByOrderId(Long orderId);

}
