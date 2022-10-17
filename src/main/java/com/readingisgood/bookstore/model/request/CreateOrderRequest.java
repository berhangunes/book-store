package com.readingisgood.bookstore.model.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<Long> bookIdList;
    private Long userId;

}
