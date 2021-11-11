package com.example.demo.services;

import com.example.demo.Request.CreateUserRequest;
import com.example.demo.entities.PhoneEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exception.ApiException;
import com.example.demo.mappers.PhoneMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.User;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.response.UserResponse;
import com.example.demo.utils.TimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      List<UserEntity>  userEntity = userRepository.findByEmail(email);
      if (userEntity.size()==0){
          throw new EntityNotFoundException("Could not find any user with email" + email);
      }
        return  UserMapper.INSTANCE.userEntityToUser(userEntity.get(0));
    }

    public Boolean validateEmailNotExists(String email){
        List<UserEntity>  userEntity = userRepository.findByEmail(email);
        if (userEntity.size()>0){
            throw new ApiException("Validation exception",
                    "The email " + email + " already exists", 422);
        }
        return true;
    }

    public Boolean validateEmail(String email){
        Pattern patternEmail= Pattern.compile("^([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))$");
        Matcher matcher=patternEmail.matcher(email);
        if (!matcher.find()){
            throw new ApiException("Validation exception",
                    "The email format is incorrect: " + email, 422);
        }
        return true;
    }

    public Boolean validatePassword(String password){
        Pattern patternPassword= Pattern.compile("^([A-Z])([a-z]+)(([0-9]){2})$");
        Matcher matcher=patternPassword.matcher(password);
        if (!matcher.find()){
            throw new ApiException("Validation exception",
                    "The password format is incorrect: " + password, 422);
        }
        return true;
    }

    public User create(CreateUserRequest createUserRequest ) {
        validateEmailNotExists(createUserRequest.getEmail());
        validateEmail(createUserRequest.getEmail());
        validatePassword(createUserRequest.getPassword());


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
       userRepository.updateLastLoginByEmail(TimeUtil.getTime(),userEntity.getEmail());
       userRepository.updateTokenByEmail(token,userEntity.getEmail());

       userEntity.setLast_login(TimeUtil.getTime());
       userEntity.setToken(token);

       return UserMapper.INSTANCE.userEntityToUser(userEntity);
    }


}
