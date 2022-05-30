package com.example.amai.api.user;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.security.dto.user.ForgotPassword;
import com.example.amai.core.security.service.OtpService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class accountUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @PostMapping("register")
    public Users register(@RequestBody Users user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        user.getAccount().setEnable(true);
        user.getAccount().setRole(ERole.ROLE_CUSTOMER);
        return userService.save(user);
    }

    @PostMapping("account/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        Optional<Account> accountOptional = this.accountService.getById(forgotPassword.getUserName());
        return accountOptional.map(account -> {
            String otpServer = this.otpService.getOtp(forgotPassword.getUserName());
            if (forgotPassword.getOtp().equals(otpServer)) {
                account.setPassword(this.passwordEncoder.encode(forgotPassword.getNewPassword()));
                this.accountService.save(account);
                this.otpService.clearOTP(forgotPassword.getUserName());
                return new ResponseEntity<>(true, HttpStatus.OK); // Success
            } else {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); // OTP fail
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // username not exists
    }

    @GetMapping("account/generate/{userName}")
    public ResponseEntity<Boolean> generateOtp(@PathVariable("userName") String userName) {

        Optional<Account> accountOptional = this.accountService.getById(userName);
        return accountOptional.map(account -> {
            if (account.getEnable()) {
                String otp = this.otpService.generateOTP(userName);
                boolean isSendOtp = this.accountService.senOtpEmail(account.getUser().getEmail(), otp);
                if (isSendOtp) {
                    return new ResponseEntity<>(true, HttpStatus.OK);// Send mail success
                } else {
                    return new ResponseEntity<>(false, HttpStatus.OK);  // Send mail fail
                }
            } else {
                return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);  // Account locked
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // Username not exists
    }
}
