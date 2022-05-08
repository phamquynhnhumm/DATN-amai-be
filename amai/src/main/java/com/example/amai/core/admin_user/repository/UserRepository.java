package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}

