package com.example.amai.core.Food.entity;

import com.example.amai.core.order.entity.OrderDetail;
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
 * Món
 */
public class Food {
    /**
     * ID dịch vụ
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
     * Tên món
     */
    @Column(name = "name")
    private String name;

    /**
     * Đơn vị tính
     */
    private String unit;

    /**
     * Giá bán
     */
    private Float price;

    /**
     * Mô  tả cách pha chế
     */
    private String describe;

    /**
     * Số lượng
     */
    private int quanity;

    /**
     * Trạng thái
     */
    private String status;

    /**
     * Danh sách chi tiết đặt món {@link OrderDetail}
     */
    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<OrderDetail> orderDetailList;

    /**
     * Danh sách chi tiết nguyên liệu {@link FoodDetail}
     */
    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<FoodDetail> foodDetailList;

    /**
     * Danh mục món {@link Food}
     */
    @ManyToOne
    @JoinColumn(name = "foodCategory_id")
    private FoodCategory foodCategory;

    /**
     * Ảnh món
     */
    private String Image;
}
