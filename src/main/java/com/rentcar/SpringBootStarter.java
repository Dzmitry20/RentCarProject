package com.rentcar;

import com.rentcar.beans.ApplicationBeans;
import com.rentcar.beans.SwaggerConfig;
import com.rentcar.security.configuration.WebSecurityConfiguration;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.rentcar")
@EnableWebMvc
@EnableSwagger2
@Import({
        ApplicationBeans.class,
        SwaggerConfig.class,
        WebSecurityConfiguration.class
    })
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
