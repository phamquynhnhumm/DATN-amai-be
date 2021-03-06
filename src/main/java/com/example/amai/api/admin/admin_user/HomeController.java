package com.example.amai.api.admin.admin_user;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.security.dto.user.LoginRequest;
import com.example.amai.core.security.dto.user.LoginResponse;
import com.example.amai.core.security.dto.user.UserRequest;
import com.example.amai.core.security.jwt.JwtUtil;
import com.example.amai.core.security.service.MyUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserRequest userRequest = null;
        String jwt = null;
        String status;
        HttpStatus httpStatus;
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(loginRequest.getUserName());
            jwt = jwtUtil.generateJwtToken(userDetails);
            Users user = this.userService.getByUserName(loginRequest.getUserName());
            userRequest = this.modelMapper.map(user, UserRequest.class);
            status = "Success";
            httpStatus = HttpStatus.OK;
        } catch (DisabledException disabledException) {
            status = "Account locked";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (BadCredentialsException badCredentialsException) {
            status = "Wong pasrsword";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            status = "Username not exists";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            status = "Error server";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new LoginResponse(jwt, userRequest, status), httpStatus);
    }

    @PostMapping("/logout")
    private ResponseEntity<?> logout() {
        return null;
    }
}
