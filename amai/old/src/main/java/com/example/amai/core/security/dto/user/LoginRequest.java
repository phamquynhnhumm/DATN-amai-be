package com.example.amai.core.security.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String password;
}
