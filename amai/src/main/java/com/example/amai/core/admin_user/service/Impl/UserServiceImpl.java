package com.example.amai.core.admin_user.service.Impl;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.repository.UserRepository;
import com.example.amai.core.admin_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Users save(Users entity) {
        return userRepository.save(entity);
    }
}