package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAccountByIsDeletedFalse();

    Boolean existsByUserName(String username);
}
