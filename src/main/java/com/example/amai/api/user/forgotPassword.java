package com.example.amai.api.user;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.security.dto.user.NewPassword;
import com.example.amai.core.security.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/forgot")
@CrossOrigin
public class forgotPassword {
    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @GetMapping("account/otpsotnewpassword/{email}")
    public ResponseEntity<Boolean> senOtpEmailNewPassword(@PathVariable("email") String email) {
        String otp = this.otpService.generateOTP(email);
        boolean isSenMail = this.accountService.senOtpEmailNewPassword(email, otp);
        if (isSenMail) {
            return new ResponseEntity<>(true, HttpStatus.OK);// Send mail success
        }
        return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);  // Account locked
    }

    @PostMapping("account/newpassword")
    public ResponseEntity<Boolean> newPassword(@RequestBody NewPassword newPassword) {
        Account account = this.accountService.findByUser_Email(newPassword.getEmail());
        String otpServer = this.otpService.getOtp(newPassword.getEmail());
        if (newPassword.getOtp().equals(otpServer)) {
            account.setPassword(this.passwordEncoder.encode(newPassword.getNewPassword()));
            account.setUpdatedBy(String.valueOf(account));
            this.accountService.save(account);
            this.otpService.clearOTP(newPassword.getNewPassword());
            return new ResponseEntity<>(true, HttpStatus.OK); // Success
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); // OTP fail
        }
    }
}
