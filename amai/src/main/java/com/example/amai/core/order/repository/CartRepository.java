package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByIsDeletedAndCreatedBy_IsDeletedAndCreatedBy_UserName (boolean idDeleteOder,boolean idAccount, String userName);
}
