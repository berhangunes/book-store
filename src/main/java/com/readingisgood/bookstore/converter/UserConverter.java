package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.persistence.entity.User;
import response.CreateUserDto;
import response.GetUserByIdDto;

public class UserConverter {
    public static CreateUserDto createUserDto(User user){
        return CreateUserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .name(user.getName())
                .age(user.getAge())
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
