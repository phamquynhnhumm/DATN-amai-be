package com.example.amai.api.admin.admin_user;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.service.AccountService;
import com.example.amai.core.admin_user.service.UserService;
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
}

