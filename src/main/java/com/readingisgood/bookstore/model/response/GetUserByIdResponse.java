package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserByIdResponse {

        private Long userId;
        private String name;
        private String email;
        private String phone;
        private String address;
}
