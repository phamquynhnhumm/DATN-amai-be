package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import com.example.amai.core.order.repository.OrderRepository;
import com.example.amai.core.order.service.OrderService;
import com.example.amai.core.security.jwt.QRUtils;
import com.sun.deploy.association.utility.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final int ORDER_QR_CODE_SIZE_WIDTH = 300;
    private static final int ORDER_QR_CODE_SIZE_HEIGHT = 300;

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
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Oder save(Oder entity) {
        return orderRepository.save(entity);
    }

    @Override
    public List<Oder> findByIsDeleted(boolean idDeleteOder, boolean issDeleteAccount) {
        return orderRepository.findByIsDeletedAndAccount_IsDeleted(idDeleteOder, issDeleteAccount);
    }

    @Override
    public List<Oder> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(boolean idDeleteOder, boolean issDeleteAccount, String userName) {
        return orderRepository.findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(idDeleteOder, issDeleteAccount, userName);
    }

    @Override
    public List<Oder> findAllByIsDeletedFalseAndStatus(EStatusOrder status) {
        return orderRepository.findAllByIsDeletedFalseAndStatus(status);
    }

    @Override
    public String generateQrCode(Oder sdi,String imagePath) {
        String prettyData = QRUtils.prettyObject(sdi);
        String qrCode = QRUtils.generateQrCode(prettyData, ORDER_QR_CODE_SIZE_WIDTH, ORDER_QR_CODE_SIZE_HEIGHT,imagePath);
        return qrCode;
    }

    @Override
    public List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone) {
        return orderRepository.findAllSerachOder(isDeleteOder, isDeleteAccount, fullName, userName, address, phone);
    }
}
