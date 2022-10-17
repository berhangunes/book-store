package com.readingisgood.bookstore.controller;


import com.readingisgood.bookstore.model.request.CreateOrderRequest;
import com.readingisgood.bookstore.model.response.CancelOrderResponse;
import com.readingisgood.bookstore.model.response.CreateOrderResponse;
import com.readingisgood.bookstore.model.response.GetOrderByIdResponse;
import com.readingisgood.bookstore.model.response.GetOrdersOfUserResponse;
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
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest.getBookIdList(), createOrderRequest.getUserId()));
    }

    @GetMapping
    @RequestMapping("/getOrdersOfUser")
    public ResponseEntity<List<GetOrdersOfUserResponse>> getOrders(@RequestParam("customerId") Long customerId){
        return ResponseEntity.ok(orderService.getOrdersOfUser(customerId));
    }

    @PatchMapping
    @RequestMapping("/cancelOrder")
    public ResponseEntity<CancelOrderResponse> cancelOrder (@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @GetMapping
    @RequestMapping("/getOrderById")
    public ResponseEntity<GetOrderByIdResponse> getOrderById (@RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
