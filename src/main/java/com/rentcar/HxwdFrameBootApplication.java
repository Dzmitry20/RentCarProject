package com.rentcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class HxwdFrameBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HxwdFrameBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HxwdFrameBootApplication.class);
    }


}

