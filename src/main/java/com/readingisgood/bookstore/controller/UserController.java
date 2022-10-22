package com.readingisgood.bookstore.controller;


import com.readingisgood.bookstore.model.request.CreateUserRequest;
import com.readingisgood.bookstore.model.response.CreateUserDto;
import com.readingisgood.bookstore.model.response.GetUserByIdDto;
import com.readingisgood.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @RequestMapping("/createuser")
    public ResponseEntity<CreateUserDto> createUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(
                createUserRequest.getUserName(),
                createUserRequest.getPassword(),
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getPhone(),
                createUserRequest.getAddress()));
    }
    @GetMapping
    @RequestMapping("/getUserById")
    public ResponseEntity <GetUserByIdDto> getUserById(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }
}
