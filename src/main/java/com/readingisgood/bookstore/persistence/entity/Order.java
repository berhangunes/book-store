package com.readingisgood.bookstore.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Table
@Getter
@Setter
@Builder
@Entity(name = "orderbook")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

   public enum Status{
        CREATED,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name="address")
    private String address;

    @Column(name = "customerId", nullable = false)
    private Long customerId;

    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.CREATED;

    @Column(name = "ordered_books")
    private Long[] orderedBooks;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
