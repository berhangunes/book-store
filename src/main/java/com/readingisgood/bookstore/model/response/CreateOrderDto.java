package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderDto {

    private Long orderId;
    private Enum status;
    private Long customerId;
    private Double totalPrice;
    private String address;
}

