package com.piphub.springjwtauthentication.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("POSTFREEAPP.COM SERVER APIS")
                        .description("Post Free App server application")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Mr.SAREN")
                                .url("https://postfreeapp.com")
                                .email("dinsarenkh@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }
}