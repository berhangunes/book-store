package com.readingisgood.bookstore.controller;

import com.readingisgood.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.CreateOrderRequest;
import response.CancelOrderDto;
import response.CreateOrderDto;
import response.GetOrderByIdDto;
import response.GetOrdersOfUserDto;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order-book")
    public ResponseEntity<CreateOrderDto> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest.getBookIdList(), createOrderRequest.getUserId()));
    }

    @GetMapping("/get-orders-of-user")
    public ResponseEntity<List<GetOrdersOfUserDto>> getOrdersOfUser(@RequestParam("customerId") Long customerId){
        return ResponseEntity.ok(orderService.getOrdersOfUser(customerId));
    }

    @PatchMapping("/cancel-order")
    public ResponseEntity<CancelOrderDto> cancelOrder(@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }


    @GetMapping("/get-order-by-id")
    public ResponseEntity<GetOrderByIdDto> getOrderById(@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
