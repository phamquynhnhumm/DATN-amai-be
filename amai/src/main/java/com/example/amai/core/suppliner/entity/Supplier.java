package com.example.amai.core.suppliner.entity;

import com.example.amai.core.Food.entity.Material;
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

    /**
     * Danh sách nguyên liệu cung cấp {@link Material}
     */
    @OneToMany(mappedBy = "supplierList")
    @JsonIgnore
    List<Material> materialList;


}
