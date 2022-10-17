package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.CreateUserResponse;
import com.readingisgood.bookstore.model.response.GetUserByIdResponse;
import com.readingisgood.bookstore.persistence.entity.User;

public class UserConverter {


    public static CreateUserResponse createUserResponse(User user){
        return CreateUserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .build();
    }

    public static GetUserByIdResponse getUserByIdResponse(User user){
        return GetUserByIdResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
