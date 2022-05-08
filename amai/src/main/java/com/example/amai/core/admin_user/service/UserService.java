package com.example.amai.core.admin_user.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.admin_user.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<Users, Integer> {
}
