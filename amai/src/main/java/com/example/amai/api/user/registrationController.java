package com.example.amai.api.user;

import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/class")
@CrossOrigin
public class registrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/create")
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        if (registration.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(registrationService.save(registration));
    }
}
