package com.rentcar.beans;

import com.rentcar.controller.requests.mappers.BillMapper;
import com.rentcar.controller.requests.mappers.OrderMapper;
import com.rentcar.controller.requests.mappers.UserCreateMapper;
import com.rentcar.controller.requests.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ApplicationBeans {

    @Bean
    public NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public UserCreateMapper userCreateMapper() {
        return Mappers.getMapper(UserCreateMapper.class);
    }

    @Bean
    public OrderMapper orderMapper() {
        return Mappers.getMapper(OrderMapper.class);
    }

    @Bean
    public BillMapper billMapper() {
        return Mappers.getMapper(BillMapper.class);
    }
}
