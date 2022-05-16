package com.example.amai.api.admin_user;

import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.security.service.MyUserDetailService;
import com.example.amai.core.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;


    @GetMapping("user")
    public String user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return ("xin chao " + name + " tai khoan use");
    }
}
