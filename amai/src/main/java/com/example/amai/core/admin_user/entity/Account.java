package com.example.amai.core.admin_user.entity;

import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.Food.entity.listener.FoodCategoryListener;
import com.example.amai.core.admin_user.entity.listener.AccountListener;
import com.example.amai.core.order.entity.Cart;
import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.registration.entity.Registration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AccountListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Tài khoản
 */
public class Account {
    @Id
    private String userName;

    /**
     * Người tạo
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * Thời gian tạo
     */
    private String createAt;
    /**
     * Người cập nhật
     */
    @Column(name = "updated_by")
    private String updatedBy;

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
     * Quyền tài khoản {@link ERole}
     */
    @Enumerated(EnumType.STRING)
    private ERole role;

    /**
     * Mật khẩu
     */
    private String password;

    /**
     * Danh sách order {{@link Oder}}
     */
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Oder> ordersList;

    /**
     * Danh sách gior hangf {{@link Oder}}
     */
    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    List<Cart> carts;
    /**
     * Danh sách order {{@link Oder}}
     */
    @OneToMany(mappedBy = "updatedBy")
    @JsonIgnore
    List<Registration> registrations;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Users user;

    public boolean getEnable() {
        return this.enable;
    }

    /**
     * Trạng thái tài khoản (true: cho phép truy cập, false: đã bị khóa)
     */
    private boolean enable;
}
