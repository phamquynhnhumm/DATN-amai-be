package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.repository.OrderRepository;
import com.example.amai.core.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Oder> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Oder> getById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Oder save(Oder entity) {
        return orderRepository.save(entity);
    }
}
