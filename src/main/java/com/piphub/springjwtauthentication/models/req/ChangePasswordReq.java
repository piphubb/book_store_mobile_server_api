package com.piphub.springjwtauthentication.models.req;

import lombok.Data;

@Data
public class ChangePasswordReq {
    private String deviceId;
    private String phoneNumber;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
