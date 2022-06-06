package com.example.amai.core.security.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewPassword {
    private String emailnew;
    private String newPassword;
    private String otp;
}
