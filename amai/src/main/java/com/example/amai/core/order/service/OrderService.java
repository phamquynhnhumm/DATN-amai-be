package com.example.amai.core.order.service;

import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.Oder;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends IService<Oder, Integer> {
    List<Oder> findByIsDeleted(boolean idDelete);

    List<FoodDetail> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, boolean fullName, String userName, String address, String phone);
}
