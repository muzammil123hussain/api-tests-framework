package com.muzammil.models.Agency;

import lombok.Data;

@Data
public class PostAgencyRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
}