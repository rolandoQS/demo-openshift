package com.example.demo.services;

import com.example.demo.Request.CreateUserRequest;
import com.example.demo.entities.UserEntity;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UserRepository userRepository;

   // private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UserRepository clientRepository) {
        this.userRepository = clientRepository;
    }

    public List<UserResponse> getAll(){
        List<UserResponse> users = UserMapper.INSTANCE.userListToUserResponse(UserMapper.INSTANCE.listUserEntityToListUser(userRepository.findAll()));
        return users;
    }

    public User create(CreateUserRequest createUserRequest) {
        User user=UserMapper.INSTANCE.createUserRequestToUser(createUserRequest);
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity userEntity=this.userRepository.save(UserMapper.INSTANCE.userToUserEntity(user));
        return UserMapper.INSTANCE.userEntityToUser(userEntity);
    }


}
