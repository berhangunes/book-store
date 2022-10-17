package com.readingisgood.bookstore.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {

    private Long userId;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
}
