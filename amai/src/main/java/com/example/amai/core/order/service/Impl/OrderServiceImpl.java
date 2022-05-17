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

    @Override
    public List<Oder> findByIsDeleted(boolean idDelete) {
        return orderRepository.findByIsDeleted(idDelete);
    }

    @Override
    public List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone) {
        return orderRepository.findAllSerachOder(isDeleteOder, isDeleteAccount, fullName, userName, address, phone);
    }
}
