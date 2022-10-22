package com.readingisgood.bookstore.controller;


import com.readingisgood.bookstore.model.request.CreateOrderRequest;
import com.readingisgood.bookstore.model.response.CancelOrderDto;
import com.readingisgood.bookstore.model.response.CreateOrderDto;
import com.readingisgood.bookstore.model.response.GetOrderByIdDto;
import com.readingisgood.bookstore.model.response.GetOrdersOfUserDto;
import com.readingisgood.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @RequestMapping("/orderBook")
    public ResponseEntity<CreateOrderDto> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest.getBookIdList(), createOrderRequest.getUserId()));
    }

    @GetMapping
    @RequestMapping("/getOrdersOfUser")
    public ResponseEntity<List<GetOrdersOfUserDto>> getOrdersOfUser(@RequestParam("customerId") Long customerId){
        return ResponseEntity.ok(orderService.getOrdersOfUser(customerId));
    }

    @PatchMapping
    @RequestMapping("/cancelOrder")
    public ResponseEntity<CancelOrderDto> cancelOrder(@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @GetMapping
    @RequestMapping("/getOrderById")
    public ResponseEntity<GetOrderByIdDto> getOrderById(@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
