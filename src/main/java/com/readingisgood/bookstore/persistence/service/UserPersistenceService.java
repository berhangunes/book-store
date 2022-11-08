package com.readingisgood.bookstore.persistence.service;


import com.readingisgood.bookstore.advice.exceptions.UserAlreadyExistsException;
import com.readingisgood.bookstore.advice.exceptions.UserNotFoundException;
import com.readingisgood.bookstore.persistence.entity.User;
import com.readingisgood.bookstore.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPersistenceService{
    private final UserRepository userRepository;
    public User createUser(String userName, String password, String name, String email, String phone, String address) {
        if (isUserNameExists(userName) != null && isUserNameExists(userName)) {
            throw new UserAlreadyExistsException();
        } else if (isEmailExists(email) !=null && isEmailExists(email)) {
            throw new UserAlreadyExistsException();
        } else if (isPhoneNumberExists(phone) != null && isPhoneNumberExists(phone)) {
            throw new UserAlreadyExistsException();
        }
        User user = User.builder().userName(userName)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
        return userRepository.save(user);
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
    public User findUserById(Long userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
    }
}
