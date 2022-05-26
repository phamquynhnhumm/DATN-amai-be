package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService extends IService<Cart, Integer> {
    List<Cart> findByIsDeletedAndCreatedBy_UserName(boolean idDeleteOder, boolean isDeleteAccount, String userName);

    Integer totalMoney(String userName);

    Integer totalQuantity(String userName);
}
