package com.rentcar.beans;

import com.rentcar.controller.requests.mappers.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ApplicationBeans {

    @Bean
    public NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public S3Client s3Client(AwsS3Config amazonConfiguration) {
        return S3Client
                .builder()
                .region(Region.of(amazonConfiguration.getRegion()))
                .credentialsProvider(() ->
                        AwsBasicCredentials.create(
                                amazonConfiguration.getAwsAccessKeyId(),
                                amazonConfiguration.getAwsSecretKey()
                        ))
                .build();
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

    @Bean
    public DiscountMapper discountMapper() {
        return Mappers.getMapper(DiscountMapper.class);
    }

    @Bean
    public CarMapper carMapper() {
        return Mappers.getMapper(CarMapper.class);
    }

    @Bean
    public BillUpdateMapper billUpdateMapper() {
        return Mappers.getMapper(BillUpdateMapper.class);
    }
}
