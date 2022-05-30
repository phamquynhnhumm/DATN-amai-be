package com.example.amai.api.user;

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
        System.out.println(email);

        String otp = RandomStringUtils.randomAlphabetic(6);
        System.out.println(otp);
        boolean isSenMail = this.accountService.senOtpEmailSinup(email, otp);
        if (isSenMail) {
            return new ResponseEntity<>(true, HttpStatus.OK);// Send mail success
        }
        return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);  // Account locked
    }
}
