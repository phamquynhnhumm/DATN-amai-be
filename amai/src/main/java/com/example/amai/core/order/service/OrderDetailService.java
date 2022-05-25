package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService extends IService<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrders_IdAndIsDeletedIsFalse(boolean idorderdetail, Integer idOders);
}
