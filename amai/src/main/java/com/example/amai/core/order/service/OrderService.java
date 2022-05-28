package com.example.amai.core.order.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends IService<Oder, Integer> {
    List<Oder> findByIsDeleted(boolean idDeleteOder, boolean issDeleteAccount);

    List<Oder> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(boolean idDeleteOder, boolean issDeleteAccount, String userName);

    List<Oder> findAllByIsDeletedFalseAndStatus(EStatusOrder status);
    String generateQrCode(Oder sdi);

    List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone);
}
