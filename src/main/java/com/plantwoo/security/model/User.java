package com.plantwoo.security.model;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String password;
    private String userId;
    private String role;
}
