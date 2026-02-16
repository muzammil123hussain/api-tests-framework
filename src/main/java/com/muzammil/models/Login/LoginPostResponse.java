package com.muzammil.models.Login;

import lombok.Data;

@Data
public class LoginPostResponse {

    private User user;
    private Tokens tokens;

    @Data
    public static class User {
        private int id;
        private String username;
        private String email;
        private String createdAt;
        private String updatedAt;
    }

    @Data
    public static class Tokens {
        private Access access;
        private Refresh refresh;
    }

    @Data
    public static class Access {
        private String token;
        private long expires;
    }

    @Data
    public static class Refresh {
        private String token;
        private long expire;
    }
}