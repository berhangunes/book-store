package com.readingisgood.bookstore.controller;

import com.readingisgood.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.CreateUserRequest;
import response.CreateUserDto;
import response.GetUserByIdDto;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<CreateUserDto> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(
                createUserRequest.getUserName(),
                createUserRequest.getPassword(),
                createUserRequest.getName(),
                createUserRequest.getAge(),
                createUserRequest.getEmail(),
                createUserRequest.getPhone(),
                createUserRequest.getAddress()));
    }
    @GetMapping("/get-user-by-id")
    public ResponseEntity <GetUserByIdDto> getUserById(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }
}
