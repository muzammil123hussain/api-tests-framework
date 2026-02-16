package com.muzammil.models.Agency;

import lombok.Data;

@Data
public class GetSingleAgencyResponse {
    private Agency response;

    @Data
    public static class Agency {
        private int id;
        private String name;
        private String address;
        private String phone;
        private String email;
        private String createdAt;
        private String updatedAt;
    }
}