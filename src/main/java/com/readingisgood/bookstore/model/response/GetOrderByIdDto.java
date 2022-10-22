package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class GetOrderByIdDto {

    private Long orderId;
    private Enum status;
    private Long customerId;
    private Double totalPrice;
    private Long[] orderedBooks;
    private String address;

}
