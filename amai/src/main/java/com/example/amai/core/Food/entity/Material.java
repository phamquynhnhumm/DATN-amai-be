package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.listener.MaterialListener;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.suppliner.entity.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(MaterialListener.class)
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
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * Danh mục nhà cung cấp {@link Supplier}
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplierList;

    /**
     * Số lượng
     */
    private Integer quantity;

    /**
     * Giá nhập
     */
    private Float price;

    /**
     * Tên nguyên liệu
     */
    private String name;


    @OneToMany(mappedBy = "material")
    @JsonIgnore
    private List<FoodDetail> foodDetailList;
}
