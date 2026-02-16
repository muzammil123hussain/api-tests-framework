package com.muzammil.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muzammil.models.Login.LoginPostRequest;

import java.util.Map;

public class LoginMapper {
    public Map<String, Object> postLoginMethod(String email, String password) {
        LoginPostRequest postLoginRequest = new LoginPostRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        postLoginRequest.setEmail(email);
        postLoginRequest.setPassword(password);
        return objectMapper.convertValue(postLoginRequest, Map.class);
    }
}