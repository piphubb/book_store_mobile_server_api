package com.piphub.springjwtauthentication.payload.request;

import lombok.Data;

@Data
public class RegisterReq {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String profile;
    private String role;

    private String status;

}
