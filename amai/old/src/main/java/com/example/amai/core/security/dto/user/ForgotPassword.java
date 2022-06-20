package com.example.amai.core.security.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassword {
    private String userName;
    private String newPassword;
    private String otp;
}
