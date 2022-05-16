package com.example.amai.core.admin_user.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.admin_user.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends IService<Account, String> {
    boolean changePassword(Account account);
    Boolean existsByUserName(String username);
}
