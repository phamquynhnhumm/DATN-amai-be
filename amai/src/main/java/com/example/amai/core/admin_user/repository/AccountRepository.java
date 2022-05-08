package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
