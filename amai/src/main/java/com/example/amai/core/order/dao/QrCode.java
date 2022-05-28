package com.example.amai.core.order.dao;

import com.example.amai.core.admin_user.entity.Account;

import javax.persistence.*;
import java.util.List;

public class QrCode {
    private Integer id;
    private Account account;
    private String createAt;
    private String fullName;
    private String phone;
    private String address;
    private String payments;
    private List<QROrderDetail> orderDetailList;
}
