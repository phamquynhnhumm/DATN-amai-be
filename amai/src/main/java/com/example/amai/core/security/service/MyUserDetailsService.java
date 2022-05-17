package com.example.amai.core.security.service;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> accountOptional = this.userService.findByEmail(email);
        return accountOptional.map(MyUserDetails::new).orElse(null);
    }

}
