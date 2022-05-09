package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService extends IService<OrderDetail, Integer> {
}
