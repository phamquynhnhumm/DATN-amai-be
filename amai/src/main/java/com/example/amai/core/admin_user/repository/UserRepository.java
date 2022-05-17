package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<Users> findByEmail(String email);

    List<Users> findUserByIsDeletedFalse();
}
