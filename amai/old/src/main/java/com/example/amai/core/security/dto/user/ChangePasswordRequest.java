package com.example.amai.core.security.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String userName;
    private String oldPassword;
    private String newPassword;
}
