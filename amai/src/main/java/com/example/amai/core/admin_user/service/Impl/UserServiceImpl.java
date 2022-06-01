package com.example.amai.core.admin_user.service.Impl;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.admin_user.repository.UserRepository;
import com.example.amai.core.admin_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userReponsitory;

    @Override
    public Boolean isEmailExists(String email) {
        return userReponsitory.existsByEmail(email);
    }

    @Override
    public Users getByUserName(String username) {
        return userReponsitory.findByAccount_UserName(username);
    }

    @Override
    public Boolean isPhoneExists(String phone) {
        return userReponsitory.existsByPhone(phone);
    }

    @Override
    public String findAllByEmail(String userName) {
        return userReponsitory.findAllByEmail(userName);
    }


    @Override
    public Optional<Users> findByEmail(String email) {
        return userReponsitory.findByEmail(email);
    }

    @Override
    public List<Users> findAllByAccount_Role(ERole role) {
        return userReponsitory.findAllByAccount_Role(role);
    }

    @Override
    public List<Users> search(String fullName, String userName, String phone, String email, String address) {
        return userReponsitory.search(fullName, userName, phone, email, address);
    }

    @Override
    public List<Users> getAll() {
        return userReponsitory.findUserByIsDeletedFalse();
    }

    @Override
    public Optional<Users> getById(Integer id) {
        return userReponsitory.findById(id);
    }

    @Override
    public Users save(Users user) {
        return userReponsitory.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        this.userReponsitory.deleteById(id);
    }
}
