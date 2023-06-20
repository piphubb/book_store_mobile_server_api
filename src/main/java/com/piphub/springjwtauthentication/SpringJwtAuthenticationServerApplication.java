package com.piphub.springjwtauthentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Slf4j
@Configuration
public class SpringJwtAuthenticationServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtAuthenticationServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringJwtAuthenticationServerApplication.class);
    }

}
