package com.example.amai.core.order.entity;

import com.example.amai.core.admin_user.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * ID đặt món
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
     * Tài khoản đặt món {{@link Account}}
     */
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
