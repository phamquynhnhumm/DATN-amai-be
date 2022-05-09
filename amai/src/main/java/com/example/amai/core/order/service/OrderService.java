package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.Oder;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends IService<Oder, Integer> {
}
