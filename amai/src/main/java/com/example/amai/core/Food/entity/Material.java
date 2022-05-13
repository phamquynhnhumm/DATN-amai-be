package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.listener.FoodCategoryListener;
import com.example.amai.core.suppliner.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(FoodCategoryListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Nguyên liệu
 */
public class Material {

    /**
     * ID nguyên liệu
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
     * Danh mục nhà cung cấp {@link Supplier}
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplierList;

    /**
     * Đơn vị tính
     */
    private String unit;

    /**
     * Số lượng
     */
    private Integer quantity;

    /**
     * Giá nhập
     */
    private Float price;

    /**
     * Mô tả
     */
    private String name;

    /**
     * Khối lượng nhập vào
     */
    private Float importKg;

    /**
     * Khối lượng còn lại
     */
    private Float remainingKg;
}
