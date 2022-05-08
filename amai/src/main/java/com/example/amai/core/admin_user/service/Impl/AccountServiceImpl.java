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
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account entity) {
        return accountRepository.save(entity);
    }
}
