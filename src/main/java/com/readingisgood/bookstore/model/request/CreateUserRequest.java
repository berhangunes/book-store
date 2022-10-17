package com.readingisgood.bookstore.model.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;


}
