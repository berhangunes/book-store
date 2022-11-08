package com.readingisgood.bookstore.persistence.service;

import com.readingisgood.bookstore.advice.exceptions.NotEnoughStockException;
import com.readingisgood.bookstore.advice.exceptions.OrderAlreadyCanceledException;
import com.readingisgood.bookstore.advice.exceptions.OrderNotFoundException;
import com.readingisgood.bookstore.persistence.entity.Order;
import com.readingisgood.bookstore.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderPersistenceService {
    private final OrderRepository orderRepository;
    private final BookPersistenceService bookPersistenceService;
    private final UserPersistenceService userPersistenceService;

    public Order createOrder(List<Long> bookIdList, Long userId) {
        List<Long> stocks = bookIdList.stream().distinct().collect(Collectors.toList());
        Integer existedStocks = null;
        Integer orderedStocks = null;
        Long [] booksArray = new Long[bookIdList.size()];
        booksArray = bookIdList.toArray(booksArray);
        for (int i = 0; i < stocks.size(); i++) {
            Long a = stocks.get(i);
            existedStocks = bookPersistenceService.getStockById(a);
        }
        Set<Long> setBookIdList = new HashSet<>(bookIdList);
        for (Long getIdFromList : setBookIdList) {
            orderedStocks = Collections.frequency(bookIdList, getIdFromList);
        }
        if (orderedStocks > existedStocks) {
            throw new NotEnoughStockException();
        }
        String address = userPersistenceService.findUserById(userId).getAddress();
        Long customerId = userPersistenceService.findUserById(userId).getUserId();
        Double price = bookIdList.stream().mapToDouble(bookPersistenceService::getBookPriceById).sum();
        Order order = Order.builder()
                .orderedBooks(booksArray)
                .totalPrice(price)
                .customerId(customerId)
                .address(address)
                .build();
        for (int i = 0; i < bookIdList.size(); i++) {
            long a = bookIdList.get(i);
            bookPersistenceService.decreaseBookStock(a);
        }
        return orderRepository.save(order);
    }
    public List<Order> getOrdersOfUser(Long customerId) {
        return orderRepository.findAll().stream().collect(Collectors.toList());
    }

    public Order cancelOrder(Long orderId){
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderNotFoundException());
        if(order.getStatus() == Order.Status.CANCELED){
            throw new OrderAlreadyCanceledException();
        }
        Long[] bookIdArray = order.getOrderedBooks();
        List<Long> bookIdList = java.util.Arrays.asList(bookIdArray);
        for(int i=0 ; i<bookIdList.size(); i++){
            long a = bookIdList.get(i);
            bookPersistenceService.increaseBookStock(a);
        }
        order.setStatus(Order.Status.CANCELED);
        return orderRepository.save(order);
    }
    public Order getOrderById(Long orderId) {
        return orderRepository.findByOrderId(orderId).orElseThrow(()-> new OrderNotFoundException());
    }
}