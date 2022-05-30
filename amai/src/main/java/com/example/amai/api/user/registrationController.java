package com.example.amai.api.user;

import com.example.amai.core.admin_user.dao.AccountSinup;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.service.RegistrationService;
import com.example.amai.core.security.service.OtpService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sinup")
@CrossOrigin
public class registrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @PostMapping("/create")
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        if (registration.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(registrationService.save(registration));
    }

    @GetMapping("account/otpsotpsinup/{email}")
    public ResponseEntity<Boolean> generateOtpSinup(@PathVariable("email") String email) {
        String otp = this.otpService.generateOTP(email);
        System.out.println(otp);
        boolean isSenMail = this.accountService.senOtpEmailSinup(email, otp);
        if (isSenMail) {
            return new ResponseEntity<>(true, HttpStatus.OK);// Send mail success
        }
        return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);  // Account locked
    }

    /**
     * @param accountSinup
     * @return Thông tin account
     */
    @PostMapping("account/register")
    public ResponseEntity<Account> CreateaccountSinup(@RequestBody AccountSinup accountSinup) {
        Account account = new Account();
        System.out.println(accountSinup.getUserName() + accountSinup.getPassword());
        String otpServer = this.otpService.getOtp(accountSinup.getEmail());
        if (accountSinup.getOtp().equals(otpServer)) {
            account.setPassword(this.passwordEncoder.encode(accountSinup.getPassword()));
            account.setUserName(accountSinup.getUserName());
            this.otpService.clearOTP(accountSinup.getUserName());
            System.out.println(accountSinup.getUserName() + accountSinup.getPassword());
            return ResponseEntity.ok(account);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param users
     * @return thêm tài khoản mới và thông tin tài khoản mới.vì QH 1-1 nên thêm User sẽ thêm đồng bộ Account
     */
    @PostMapping("user/create")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.save(users));
    }

}
