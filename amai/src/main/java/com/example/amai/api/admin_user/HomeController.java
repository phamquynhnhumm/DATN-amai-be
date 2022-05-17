package com.example.amai.api.admin_user;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.security.dto.user.LoginRequest;
import com.example.amai.core.security.dto.user.LoginResponse;
import com.example.amai.core.security.dto.user.RegisterRequest;
import com.example.amai.core.security.jwt.JwtUtil;
import com.example.amai.core.security.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateJwtToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getFullName(), userDetails.getUsername(),
                    userDetails.getAuthorities().toString()));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Account user) throws Exception {
        return ResponseEntity.ok(accountService.save(user));
    }

    @PostMapping("/logout")
    private ResponseEntity<?> logout() {
        return null;
    }
}
