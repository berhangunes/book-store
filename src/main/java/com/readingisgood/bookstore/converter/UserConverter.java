package com.readingisgood.bookstore.converter;

import com.readingisgood.bookstore.persistence.entity.UserEntity;
import response.CreateUserDto;
import response.GetUserByIdDto;

public class UserConverter {
    public static CreateUserDto createUserDto(UserEntity userEntity){
        return CreateUserDto.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .phone(userEntity.getPhone())
                .build();
    }
    public static GetUserByIdDto getUserByIdDto(UserEntity userEntity){
        return GetUserByIdDto.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .address(userEntity.getAddress())
                .build();
    }
}
