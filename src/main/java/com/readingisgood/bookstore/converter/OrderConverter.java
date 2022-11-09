package com.readingisgood.bookstore.converter;


import com.readingisgood.bookstore.persistence.entity.OrderEntity;
import response.CancelOrderDto;
import response.CreateOrderDto;
import response.GetOrderByIdDto;
import response.GetOrdersOfUserDto;

public class OrderConverter {
    public static GetOrdersOfUserDto getOrdersOfUserDto(OrderEntity orderEntity){
        return GetOrdersOfUserDto.builder()
                .orderId(orderEntity.getOrderId())
                .status(orderEntity.getStatus())
                .totalPrice(orderEntity.getTotalPrice())
                .build();
    }
    public static CreateOrderDto createOrderDto(OrderEntity orderEntity){
        return CreateOrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .status(orderEntity.getStatus())
                .customerId(orderEntity.getCustomerId())
                .totalPrice(orderEntity.getTotalPrice())
                .address(orderEntity.getAddress())
                .build();
    }
    public static CancelOrderDto cancelOrderDto(OrderEntity orderEntity){
        return CancelOrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .status(orderEntity.getStatus())
                .customerId(orderEntity.getCustomerId())
                .totalPrice(orderEntity.getTotalPrice())
                .build();
    }
    public static GetOrderByIdDto getOrderByIdDto(OrderEntity orderEntity){
        return GetOrderByIdDto.builder()
                .orderId(orderEntity.getOrderId())
                .status(orderEntity.getStatus())
                .customerId(orderEntity.getCustomerId())
                .totalPrice(orderEntity.getTotalPrice())
                .address(orderEntity.getAddress())
                .orderedBooks(orderEntity.getOrderedBooks())
                .build();
    }
}
