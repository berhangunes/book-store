package com.readingisgood.bookstore.persistence.service;


import com.readingisgood.bookstore.advice.exceptions.UserAlreadyExistsException;
import com.readingisgood.bookstore.advice.exceptions.UserNotFoundException;
import com.readingisgood.bookstore.persistence.entity.UserEntity;
import com.readingisgood.bookstore.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPersistenceService{
    private final UserRepository userRepository;
    public UserEntity createUser(String userName, String password, String name, Integer age, String email, String phone, String address) {
        if (isUserNameExists(userName) != null && isUserNameExists(userName)) {
            throw new UserAlreadyExistsException();
        } else if (isEmailExists(email) !=null && isEmailExists(email)) {
            throw new UserAlreadyExistsException();
        } else if (isPhoneNumberExists(phone) != null && isPhoneNumberExists(phone)) {
            throw new UserAlreadyExistsException();
        }
        UserEntity userEntity = UserEntity.builder().userName(userName)
                .password(password)
                .name(name)
                .age(age)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
        return userRepository.save(userEntity);
    }
    public Boolean isUserNameExists(String userName){
        UserEntity userEntity = userRepository.userName(userName);
        return Objects.nonNull(userEntity);
    }
    public Boolean isEmailExists(String email){
        UserEntity userEntity = userRepository.email(email);
        return Objects.nonNull(userEntity);
    }
    public Boolean isPhoneNumberExists(String phone){
        UserEntity userEntity = userRepository.phone(phone);
        return Objects.nonNull(userEntity);
    }
    public UserEntity findUserById(Long userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
    }
}
