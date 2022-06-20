package com.example.amai.core.security.service;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> accountOptional = this.accountService.findById(userName);
        return accountOptional.map(MyUserDetails::new).orElse(null);
    }
}
