package com.example.amai.core.admin_user.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.dao.ForgotPassword;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService extends IService<Account, String> {
    Boolean isUsernameExists(String username);

    Optional<Account> findById(String username);

    boolean changePassword(ForgotPassword forgotPassword);

}
