package com.readingisgood.bookstore.service;


import com.readingisgood.bookstore.converter.OrderConverter;
import com.readingisgood.bookstore.persistence.service.OrderPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.CancelOrderDto;
import response.CreateOrderDto;
import response.GetOrderByIdDto;
import response.GetOrdersOfUserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderPersistenceService orderPersistenceService;
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "Order", allEntries = true),
            @CacheEvict(value = "Books", allEntries = true)
    })
    public CreateOrderDto createOrder(List<Long> bookIdList, Long userId) {
        return OrderConverter.createOrderDto(orderPersistenceService.createOrder(bookIdList,userId));
    }
    @Cacheable(value = "Order")
    public List<GetOrdersOfUserDto> getOrdersOfUser(Long customerId) {
        return orderPersistenceService.getOrdersOfUser(customerId).stream().map(OrderConverter::getOrdersOfUserDto).collect(Collectors.toList());
    }
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "Order", allEntries = true),
            @CacheEvict(value = "Books", allEntries = true)
    })
    public CancelOrderDto cancelOrder(Long orderId){
        return OrderConverter.cancelOrderDto(orderPersistenceService.cancelOrder(orderId));
    }
    public GetOrderByIdDto getOrderById(Long orderId){
        return OrderConverter.getOrderByIdDto(orderPersistenceService.getOrderById(orderId));
    }
}
