package com.example.demo.mappers;


import com.example.demo.Request.CreateUserRequest;
import com.example.demo.entities.UserEntity;
import com.example.demo.models.User;
import com.example.demo.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CommonMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
    List<User> listUserEntityToListUser(List<UserEntity> userEntities);
    UserResponse userToUserResponse(User user);
    List<UserResponse> userListToUserResponse(List<User> users);
    User createUserRequestToUser(CreateUserRequest createUserRequest);

}
