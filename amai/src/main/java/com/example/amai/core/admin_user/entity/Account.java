package com.example.amai.core.admin_user.entity;

import com.example.amai.core.admin_user.entity.contans.ERole;
import com.example.amai.core.order.entity.Oder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate createAt;
    /**
     * Người cập nhật
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Thời gian cập nhật
     */
    private LocalDate updateAt;

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
    List<Oder> ordersList;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Users user;
}
