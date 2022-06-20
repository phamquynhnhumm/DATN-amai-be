package com.example.amai.core.suppliner.entity;

import com.example.amai.core.Food.entity.Material;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.suppliner.entity.listener.SupplierListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(SupplierListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Nhà cung cấp
 */
public class Supplier {
    /**
     * ID nhà cung cấp
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Người tạo
     */
    @ManyToOne
    @JoinColumn(name = "created_by_user_name")
    private Account createdBy;

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
    private Boolean isDeleted;

    /**
     * Tên nhà cung cấp
     */
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
    private String phone;

    /**
     * Danh sách nguyên liệu cung cấp {@link Material}
     */
    @OneToMany(mappedBy = "supplierList")
    @JsonIgnore
    List<Material> materialList;
}
