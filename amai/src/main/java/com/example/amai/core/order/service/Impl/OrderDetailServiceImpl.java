package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.OrderDetail;
import com.example.amai.core.order.repository.OrderDetailRepository;
import com.example.amai.core.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> getById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    public List<OrderDetail> findAllByOrders_IdAndIsDeletedIsFalse(boolean idorderdetail,Integer idOders) {
        return orderDetailRepository.findByIsDeletedAndOrders_Id(idorderdetail,idOders);
    }
}