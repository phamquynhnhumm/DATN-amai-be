package com.example.amai.core.suppliner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Nhà cung cấp
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class suppliner {
    /**
     * ID nhà cung cấp
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
     * Tên nhà cung cấp
     */
    @Column(name = "name")
    private String name;

    /**
     * Email nhà cung cấp
     */
    private String email;

    /**
     * Địa chỉ nhà cung cấp
     */
    private String address;

    /**
     * Số điện thoại nhà cung cấp
     */
    private int phone;

}
