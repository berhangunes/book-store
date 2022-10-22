package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.CancelOrderDto;
import com.readingisgood.bookstore.model.response.CreateOrderDto;
import com.readingisgood.bookstore.model.response.GetOrderByIdDto;
import com.readingisgood.bookstore.model.response.GetOrdersOfUserDto;
import com.readingisgood.bookstore.persistence.entity.Order;

public class OrderConverter {



    public static GetOrdersOfUserDto getOrdersOfUserDto(Order order){
        return GetOrdersOfUserDto.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static CreateOrderDto createOrderDto(Order order){
        return CreateOrderDto.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .build();
    }

    public static CancelOrderDto cancelOrderDto(Order order){
        return CancelOrderDto.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static GetOrderByIdDto getOrderByIdDto(Order order){
        return GetOrderByIdDto.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .orderedBooks(order.getOrderedBooks())
                .build();
    }
}
