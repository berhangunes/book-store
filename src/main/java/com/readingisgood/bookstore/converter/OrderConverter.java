package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.CancelOrderResponse;
import com.readingisgood.bookstore.model.response.CreateOrderResponse;
import com.readingisgood.bookstore.model.response.GetOrderByIdResponse;
import com.readingisgood.bookstore.model.response.GetOrdersOfUserResponse;
import com.readingisgood.bookstore.persistence.entity.Order;

public class OrderConverter {



    public static GetOrdersOfUserResponse getOrdersOfUser(Order order){
        return GetOrdersOfUserResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static CreateOrderResponse createOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .build();
    }

    public static CancelOrderResponse cancelOrder(Order order){
        return CancelOrderResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static GetOrderByIdResponse getOrderByIdResponse(Order order){
        return GetOrderByIdResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .orderedBooks(order.getOrderedBooks())
                .build();
    }
}
