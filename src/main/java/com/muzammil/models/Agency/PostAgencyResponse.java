package com.muzammil.models.Agency;

import lombok.Data;

@Data
public class PostAgencyResponse {
    private Agency agency;

    @Data
    public static class Agency {
        private int id;
        private String name;
        private String email;
        private String phone;
        private String address;
        private String createdAt;
        private String updatedAt;
    }
}