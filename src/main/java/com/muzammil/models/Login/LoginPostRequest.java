package com.muzammil.models.Login;

import lombok.Data;

@Data
public class LoginPostRequest {
    private String email;
    private String password;
}