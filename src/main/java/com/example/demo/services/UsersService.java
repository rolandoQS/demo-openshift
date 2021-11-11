package com.example.demo.services;

import com.example.demo.Request.CreateUserRequest;
import com.example.demo.entities.PhoneEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.mappers.PhoneMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Phone;
import com.example.demo.models.User;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.response.UserResponse;
import com.example.demo.utils.TimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;



    public UsersService(UserRepository clientRepository,PhoneRepository phoneRepository) {
        this.userRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<UserResponse> getAll(){
        List<UserResponse> users = UserMapper.INSTANCE.userListToUserResponse(UserMapper.INSTANCE.listUserEntityToListUser(userRepository.findAll()));
        return users;
    }

    public User findUserByEmail(String email){
       UserEntity userEntity = userRepository.findByEmail(email).get(0);
        return  UserMapper.INSTANCE.userEntityToUser(userEntity);
    }

    public User create(CreateUserRequest createUserRequest ) {

        User user=UserMapper.INSTANCE.createUserRequestToUser(createUserRequest);
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreated(TimeUtil.getTime());
        user.setModified(TimeUtil.getTime());
        user.setLast_login(TimeUtil.getTime());
        user.setIs_active(true);
        UserEntity userEntity=this.userRepository.save(UserMapper.INSTANCE.userToUserEntity(user));

        Collection<PhoneEntity> phones = PhoneMapper.INSTANCE.listPhoneToListPhoneEntity(PhoneMapper.INSTANCE.createPhoneRequestListToListPhone(createUserRequest.getPhones()));
        phones.stream().forEach(phone -> phone.setUser(userEntity));
        phoneRepository.saveAll(phones);

        return UserMapper.INSTANCE.userEntityToUser(userEntity);
    }

    public User loginUpdate(String token, String email){
       UserEntity userEntity = userRepository.findByEmail(email).get(0);
       userRepository.updateLastLoginById(TimeUtil.getTime(),userEntity.getEmail());
       userRepository.updateTokenById(token,userEntity.getEmail());

       userEntity.setLast_login(TimeUtil.getTime());
       userEntity.setToken(token);

       return UserMapper.INSTANCE.userEntityToUser(userEntity);
    }


}
