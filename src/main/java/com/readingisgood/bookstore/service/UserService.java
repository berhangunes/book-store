package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.converter.UserConverter;
import com.readingisgood.bookstore.model.response.CreateUserResponse;
import com.readingisgood.bookstore.model.response.GetUserByIdResponse;
import com.readingisgood.bookstore.persistence.entity.User;
import com.readingisgood.bookstore.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createUser(String userName, String password, String name, String email, String phone, String address) {

        if (isUserNameExists(userName) != null && isUserNameExists(userName)) {
            throw new RuntimeException("User already exists.");
        } else if (isEmailExists(email) !=null && isEmailExists(email)) {
            throw new RuntimeException("Email already exists.");
        } else if (isPhoneNumberExists(phone) != null && isPhoneNumberExists(phone)) {
            throw new RuntimeException("Phone Number already exists.");
        }
            User user = User.builder().userName(userName)
                    .password(password)
                    .name(name)
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .build();
            userRepository.save(user);

            CreateUserResponse newUser = UserConverter.createUserResponse(user);

            return newUser;
    }
    public Boolean isUserNameExists(String userName){
         User user = userRepository.userName(userName);
         return Objects.nonNull(user);
    }
    public Boolean isEmailExists(String email){
        User user = userRepository.email(email);
        return Objects.nonNull(user);
    }

    public Boolean isPhoneNumberExists(String phone){
        User user = userRepository.phone(phone);
        return Objects.nonNull(user);
    }

    public GetUserByIdResponse findUserById(Long userId){
            User getUser =  userRepository.findByUserId(userId);
        return UserConverter.getUserByIdResponse(getUser);
    }
}
