package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    @Query(value = "select email from users where account_user_name = :userName", nativeQuery = true)
    String findAllByEmail(@Param("userName") String userName);

    Optional<Users> findByEmail(String email);

    Users findByAccount_UserName(String username);

    List<Users> findUserByIsDeletedFalse();

    List<Users> findAllByAccount_Role(ERole role);
}
