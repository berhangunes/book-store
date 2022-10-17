package com.readingisgood.bookstore.service;


import com.readingisgood.bookstore.converter.OrderConverter;
import com.readingisgood.bookstore.model.response.CancelOrderResponse;
import com.readingisgood.bookstore.model.response.CreateOrderResponse;
import com.readingisgood.bookstore.model.response.GetOrderByIdResponse;
import com.readingisgood.bookstore.model.response.GetOrdersOfUserResponse;
import com.readingisgood.bookstore.persistence.entity.Order;
import com.readingisgood.bookstore.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final BookService bookService;
    private final OrderRepository orderRepository;

    private final UserService userService;

    public CreateOrderResponse createOrder(List<Long> bookIdList, Long userId) {
        List<Long> stocks = bookIdList.stream().distinct().collect(Collectors.toList());
        Integer existedStocks = null;
        Integer orderedStocks = null;
        Long [] booksArray = new Long[bookIdList.size()];
        booksArray = bookIdList.toArray(booksArray);

        for (int i = 0; i < stocks.size(); i++) {
            Long a = stocks.get(i);
            existedStocks = bookService.getStockById(a);
        }

        Set<Long> setBookIdList = new HashSet<>(bookIdList);
        for (Long getIdFromList : setBookIdList) {
            orderedStocks = Collections.frequency(bookIdList, getIdFromList);
        }

        if (orderedStocks > existedStocks) {
            throw new RuntimeException("Not enough stock");
        }

        String address = userService.findUserById(userId).getAddress();
        Long customerId = userService.findUserById(userId).getUserId();

        Double price = bookIdList.stream().mapToDouble(bookService::getBookPriceById).sum();

        Order order = Order.builder()
                .orderedBooks(booksArray)
                .totalPrice(price)
                .customerId(customerId)
                .address(address)
                .build();

        for (int i = 0; i < bookIdList.size(); i++) {
            long a = bookIdList.get(i);
            bookService.decreaseBookStock(a);
        }
        Order newOrder = orderRepository.save(order);
        return OrderConverter.createOrderResponse(newOrder);
    }

    public List<GetOrdersOfUserResponse> getOrdersOfUser(Long customerId) {
        List<GetOrdersOfUserResponse> getOrdersOfUserResponse = orderRepository.findAll().stream().map(OrderConverter::getOrdersOfUser).collect(Collectors.toList());
        return getOrdersOfUserResponse;
    }

    public CancelOrderResponse cancelOrder(Long orderId){

        if(orderRepository.findByOrderId(orderId).getStatus() == Order.Status.CANCELED){
            throw new RuntimeException("Order already canceled.");
        }

       Long[] bookIdArray = orderRepository.findByOrderId(orderId).getOrderedBooks();
       List<Long> bookIdList = java.util.Arrays.asList(bookIdArray);

        for(int i=0 ; i<bookIdList.size(); i++){
            long a = bookIdList.get(i);
            bookService.increaseBookStock(a);
        }
            orderRepository.findByOrderId(orderId).setStatus(Order.Status.CANCELED);
            Order canceledOrder = orderRepository.findByOrderId(orderId);
        return OrderConverter.cancelOrder(orderRepository.save(canceledOrder));
    }

    public GetOrderByIdResponse getOrderById(Long orderId){
        Order order = orderRepository.findByOrderId(orderId);
        return OrderConverter.getOrderByIdResponse(order);
    }

}
