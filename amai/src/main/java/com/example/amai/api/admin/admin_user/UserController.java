package com.example.amai.api.admin.admin_user;

import com.example.amai.core.admin_user.dao.AccountSinup;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
import com.example.amai.core.security.dto.user.ForgotPassword;
import com.example.amai.core.security.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    /**
     * Danh sách người dùng
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Users>> finAll() {
        List<Users> usersList = userService.getAll();
        return usersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    /**
     * Xóa người dùng (cập nhật cơ xóa isDelete = true
     *
     * @param id ID người dùng
     * @return người dùng đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable("id") Integer id) {
        Users users = userService.getById(id).orElse(null);
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            users.setIsDeleted(true);
            users.getAccount().setIsDeleted(true);
            userService.save(users);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /**
     * Khóa tài khoản người dùng
     *
     * @param id ID người dùng
     * @return người dùng đã được cập nhật cờ xóa
     */
    @PutMapping("enable/{id}")
    public ResponseEntity<Users> enableUser(@PathVariable("id") Integer id) {
        Users users = userService.getById(id).orElse(null);
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            users.getAccount().setEnable(false);
            userService.save(users);
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
    public ResponseEntity<Users> findById(@PathVariable("id") Integer id) {
        Users users = userService.getById(id).orElse(null);
        return users.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findByRole/{role}/{isDeleted}")
    public ResponseEntity<List<Users>> findAllByAccount_Role(@PathVariable("isDeleted") boolean isDeleted,
            @PathVariable("role") ERole role) {
        List<Users> usersList = userService.findAllByAccount_Role(role,isDeleted);
        return usersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    /**
     * Thêm mới người dùng
     *
     * @param users
     * @return
     */
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.save(users));
    }

    /**
     * Cập nhật người dùng
     *
     * @param users
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Users> editUser(@RequestBody Users users) {
        Optional<Users> usersOptional = userService.getById(users.getId());
        if (!usersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(users), HttpStatus.OK);
    }

    @PostMapping("/register")
    public Users sinup(@RequestBody Users users) {
        users.getAccount().setPassword(passwordEncoder.encode(users.getAccount().getPassword()));
        users.getAccount().setEnable(true);
        users.getAccount().setRole(ERole.ROLE_CUSTOMER);
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Users>> searchOder(@RequestParam("fullName") String fullName,
                                                  @RequestParam("userName") String userName,
                                                  @RequestParam("phone") String phone,
                                                  @RequestParam("email") String email,
                                                  @RequestParam("address") String address) {
        List<Users> usersList = userService.search(fullName, userName, phone, email, address);
        return usersList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    @PutMapping("undelete/{id}")
    public ResponseEntity<Users> undeleteCustomer(@PathVariable("id") Integer id) {
        Users users = userService.getById(id).orElse(null);
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            users.setIsDeleted(false);
            userService.save(users);
            return new ResponseEntity<>(HttpStatus.OK);
        }
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


    /**
     * Hiển thị tất cả người dùng không có email là email được truyền vào
     *
     * @param email
     * @return Nhằm mục đích khi update user sẽ loại bỏ các trường hợp email đã có trong data và trường hoipwj người dùng nhập emal hiện tại của tài khoản vẫn chấp nhận
     */
    @GetMapping("findallnotemail/{email}")
    public ResponseEntity<List<Users>> findUserByNotAccount_Email(@PathVariable("email") String email) {
        List<Users> usersList = userService.findUserByNotAccount_Email(email);
        return usersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("account/otpsotpsinup/{email}")
    public ResponseEntity<Boolean> generateOtpSinup(@PathVariable("email") String email) {
        String otp = this.otpService.generateOTP(email);
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
        String otpServer = this.otpService.getOtp(accountSinup.getEmail());
        if (accountSinup.getOtp().equals(otpServer)) {
            account.setPassword(this.passwordEncoder.encode(accountSinup.getPassword()));
            account.setUserName(accountSinup.getUserName());
            this.otpService.clearOTP(accountSinup.getUserName());
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
    public ResponseEntity<Users> createUserAdmin(@RequestBody Users users) {
        users.getAccount().setRole(ERole.ROLE_MANAGEMENT);
        if (users.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.save(users));
    }

    /**
     * Hiển thị tất cả các tài khoản user nhằm check email trùng v
     */
    @GetMapping("userlist")
    public ResponseEntity<List<Users>> finAllUser() {
        List<Users> usersList = userService.getAll();
        return usersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
