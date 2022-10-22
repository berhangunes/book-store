package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.model.response.CreateUserDto;
import com.readingisgood.bookstore.model.response.GetUserByIdDto;
import com.readingisgood.bookstore.persistence.entity.User;

public class UserConverter {


    public static CreateUserDto createUserDto(User user){
        return CreateUserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .build();
    }

    public static GetUserByIdDto getUserByIdDto(User user){
        return GetUserByIdDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
