package com.example.demo.mappers;


import com.example.demo.Request.CreatePhoneRequest;
import com.example.demo.Request.CreateUserRequest;
import com.example.demo.entities.PhoneEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.models.Phone;
import com.example.demo.models.User;
import com.example.demo.response.PhoneResponse;
import com.example.demo.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {CommonMapper.class})
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    Phone phoneEntityToPhone(PhoneEntity phoneEntity);
    PhoneEntity phoneToPhoneEntity(Phone phone);
    List<Phone> listPhoneEntityToListPhone(List<PhoneEntity> phoneEntities);
    List<PhoneEntity> listPhoneToListPhoneEntity(Collection<Phone> phones);
    PhoneResponse phoneToPhoneResponse(Phone phone);
    List<PhoneResponse> phoneListToListPhoneResponse(List<Phone> phones);
    Phone createPhoneRequestToPhone(CreatePhoneRequest createPhoneRequest);
    List<Phone> createPhoneRequestListToListPhone(Collection<CreatePhoneRequest> createPhoneRequestList);


}
