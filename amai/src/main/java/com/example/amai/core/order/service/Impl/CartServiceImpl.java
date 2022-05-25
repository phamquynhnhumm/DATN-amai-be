package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.Cart;
import com.example.amai.core.order.entity.contans.EStatusCart;
import com.example.amai.core.order.repository.CartRepository;
import com.example.amai.core.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getById(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart entity) {
        return cartRepository.save(entity);
    }

    @Override
    public List<Cart> findByIsDeletedAndCreatedBy_UserName(boolean idDeleteOder,  boolean isDeleteAccount, String userName) {
        return cartRepository.findByIsDeletedAndCreatedBy_IsDeletedAndCreatedBy_UserName(idDeleteOder, isDeleteAccount, userName);
    }
}
