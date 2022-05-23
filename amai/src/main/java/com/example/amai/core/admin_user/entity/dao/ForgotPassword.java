package com.example.amai.core.admin_user.entity.dao;

import com.example.amai.core.admin_user.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassword {
    private Account account;
    private String newPassword;
}
