package com.example.amai.core.admin_user.service.Impl;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.repository.AccountRepository;
import com.example.amai.core.admin_user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountReponsitory;

    @Override
    public Boolean isUsernameExists(String username) {
        return accountReponsitory.existsByUserName(username);
    }

    @Override
    public Optional<Account> findById(String username) {
        return accountReponsitory.findById(username);
    }

    @Override
    public List<Account> getAll() {
        return accountReponsitory.findAccountByIsDeletedFalse();
    }

    @Override
    public Optional<Account> getById(String id) {
        return accountReponsitory.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountReponsitory.save(account);
    }
}
