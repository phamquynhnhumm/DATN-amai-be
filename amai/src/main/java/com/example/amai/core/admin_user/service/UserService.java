package com.example.amai.core.admin_user.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.admin_user.entity.Users;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends IService<Users, Integer> {
    Boolean isEmailExists(String email);
    Users getByUserName(String username);

    Boolean isPhoneExists(String phone);
    String findAllByEmail(String userName);

    Optional<Users> findByEmail(String email);
}

