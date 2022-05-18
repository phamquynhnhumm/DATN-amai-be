package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.Oder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends IService<Oder, Integer> {
    List<Oder> findByIsDeleted(boolean idDeleteOder, boolean issDeleteAccount);

    List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone);
}
