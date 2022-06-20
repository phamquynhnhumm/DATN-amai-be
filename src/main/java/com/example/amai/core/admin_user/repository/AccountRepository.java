package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAccountByIsDeletedFalse();

    Boolean existsByUserName(String username);

    Account findByUser_Email(String email);


}
