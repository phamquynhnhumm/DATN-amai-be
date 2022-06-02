package com.example.amai.core.shop.entity;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import com.example.amai.core.shop.entity.listener.ShopListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

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
     * Số điên thoại cửa hàng
     */
    private String phone;
    /**
     * Khẩu hiệu của cửa hàng
     */
    private String slogen;

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
}
