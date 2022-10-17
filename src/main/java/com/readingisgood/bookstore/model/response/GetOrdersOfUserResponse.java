package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrdersOfUserResponse {

    private Long orderId;
    private Double totalPrice;
    private Enum status;


}
