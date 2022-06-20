package com.example.amai.api.admin.registration;

import com.example.amai.core.Food.entity.Material;
import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import com.example.amai.core.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/admin/class")
public class ClassController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/status/{handle}")
    public ResponseEntity<List<Registration>> finAllHandle(@PathVariable("handle") EStatuasHandle handle) {
        List<Registration> registrations = registrationService.findAllByIsDeletedFalseAndHandle(handle);
        return registrations.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Registration> deleteUser(@PathVariable("id") Integer id) {
        Registration registration = registrationService.getById(id).orElse(null);
        if (registration.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            registration.setIsDeleted(true);
            registrationService.save(registration);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một người dùng
     *
     * @param id Id của người dùng
     * @return trả về thông tin người dùng nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Registration> findById(@PathVariable("id") Integer id) {
        Registration registration = registrationService.getById(id).orElse(null);
        return registrationService.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(registration, HttpStatus.OK);
    }

    @PutMapping("undelete/{id}")
    public ResponseEntity<Registration> undeleteRegistration(@PathVariable("id") Integer id) {
        Registration registration = registrationService.getById(id).orElse(null);
        if (registration.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            registration.setIsDeleted(false);
            registrationService.save(registration);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("")
    public ResponseEntity<Registration> updteRegistration(@RequestBody Registration registration) {
        Optional<Registration> registrationOptional = registrationService.getById(registration.getId());
        if (!registrationOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(registrationService.save(registration), HttpStatus.OK);
    }

    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Registration>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Registration> registrationList = registrationService.findAllByIsDeleted(isdelete);
        return registrationList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(registrationList, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Registration>> searcMaterial(@RequestParam("isDelete") boolean isDelete,
                                                        @RequestParam("name") String name,
                                                        @RequestParam("phone") String phone) {
        List<Registration> registrations = registrationService.findSearch(isDelete, name, phone);
        return registrations.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(registrations, HttpStatus.OK);
    }
}
