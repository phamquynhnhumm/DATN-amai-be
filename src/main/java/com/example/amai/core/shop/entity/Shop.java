package com.example.amai.core.shop.entity;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.shop.entity.listener.ShopListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(ShopListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    /**
     * ID danh mục
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Thời gian cập nhật
     */
    private String updateAt;

    /**
     * Người cập nhật
     */
    @ManyToOne
    @JoinColumn(name = "updated_by_user_name")
    private Account updatedBy;

    /**
     * Địa chỉ cửa hàng
     */
    private String address;

    /**
     * Tên cửa hàng
     */
    private String nameShop;

    /**
     * Số điên thoại cửa hàng
     */
    private String phone;

    /**
     * Khẩu hiệu của cửa hàng
     */
    private String slogen;
    private String logo;
    private String email;

    /**
     * Tên tài khoản
     */
    private String nameTransfer;

    /**
     * Số tài khoản
     */
    private Integer number;

    /**
     * Tên ngân hàng chi nhánh
     */
    private String bankName;
    /**
     * Đoạn Giới thiệu ngắn
     */
    private String content;
    private String facebook;
    private String youtube;
    private String instagram;
}