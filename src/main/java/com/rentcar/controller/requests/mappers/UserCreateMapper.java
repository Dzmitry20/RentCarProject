package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.requests.UserRequest;
import com.rentcar.domain.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserCreateRequest(UserCreateRequest userCreateRequest, @MappingTarget User user);

}