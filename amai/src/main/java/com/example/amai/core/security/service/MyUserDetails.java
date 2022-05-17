package com.example.amai.core.security.service;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String fullName;

    private Account account;

    private String email;

    private String phone;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(Users users) {
        this.fullName = users.getFullName();
        this.account = users.getAccount();
        this.email = users.getEmail();
        this.phone = users.getPhone();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(users.getAccount().getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUserName();
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
