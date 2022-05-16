package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    /**
     * Exists kiểm tra sự tồn tại của tên tài khoản ( nhằm mục đích tên tài khoản không được trùng nhau)
     *
     * @param username Tên tài khoản
     * @return True nếu đã tồn tại , false nếu chưa tồn taị
     */
    boolean existsByUserName(String username);
}
