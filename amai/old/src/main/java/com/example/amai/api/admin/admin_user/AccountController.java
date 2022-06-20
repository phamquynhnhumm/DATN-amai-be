package com.example.amai.api.admin.admin_user;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Danh sách tài khoản
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Account>> finAll() {
        List<Account> accountList = accountService.getAll();
        return accountList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    /**
     * Xóa tài khoản (cập nhật cơ xóa isDelete = true
     *
     * @param userName userName người dùng
     * @return tài khoản đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{userName}")
    public ResponseEntity<Account> deleteUser(@PathVariable("userName") String userName) {
        Account account = accountService.getById(userName).orElse(null);
        if (account.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            account.setIsDeleted(true);
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một người dùng
     *
     * @param userName userName của người dùng
     * @return trả về thông tin tài khoản nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{userName}")
    public ResponseEntity<Account> findById(@PathVariable("userName") String userName) {
        Account account = accountService.getById(userName).orElse(null);
        return account.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * Thêm mới tài khoản
     *
     * @param account
     * @return
     */
    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody Account account) {
        if (account.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(accountService.save(account));
    }

    /**
     * Cập nhật tài khoản
     *
     * @param account
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Account> editUser(@RequestBody Account account) {
        Optional<Account> accountOptional = accountService.getById(account.getUserName());
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
    }
}
