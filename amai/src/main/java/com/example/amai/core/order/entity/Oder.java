package com.example.amai.core.order.entity;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.Food.entity.listener.FoodCategoryListener;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import com.example.amai.core.order.entity.listener.OrderListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(OrderListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Đặt món
 */
public class Oder {

    /**
     * ID đặt món
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Người tạo
     */
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    /**
     * Thời gian tạo
     */
    private String createAt;
    /**
     * Người cập nhật
     */
    @ManyToOne
    @JoinColumn(name = "updated_by_user_name")
    private Account updatedBy;

    /**
     * Thời gian cập nhật
     */
    private String updateAt;

    /**
     * Cờ xóa
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * Địa chỉ đặt món
     */
    private String address;

    /**
     * Số điện thoại đặt món
     */
    private String phone;

    /**
     * Tên người nhận
     */
    private String fullName;

    /**
     * Mã QR đặt món
     */
    private String qrcode;

    /**
     * Trạng thái đặt món {@link EStatusOrder}
     */
    @Enumerated(EnumType.STRING)
    private EStatusOrder status;

    /**
     * Danh sách chi tiết đặt món{@link OrderDetail}
     */
    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    private List<OrderDetail> orderDetailList;

    /**
     * Tổng tiền
     */
    private Float money;

    /**
     * Số lượng
     */
    private int quantity;
}
