package com.readingisgood.bookstore.service;


import com.readingisgood.bookstore.converter.UserConverter;
import com.readingisgood.bookstore.persistence.service.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import response.CreateUserDto;
import response.GetUserByIdDto;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPersistenceService userPersistenceService;
    public CreateUserDto createUser(String userName, String password, String name, String email, String phone, String address){
            return UserConverter.createUserDto(userPersistenceService.createUser(userName,password,name,email,phone,address));
    }
    @Cacheable(value = "user")
    public GetUserByIdDto findUserById(Long userId){
        return UserConverter.getUserByIdDto(userPersistenceService.findUserById(userId));
    }
}
