package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.contans.EStatusFood;
import com.example.amai.core.Food.entity.listener.FoodListener;
import com.example.amai.core.order.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(FoodListener.class)
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
    private String createdBy;

    /**
     * Thời gian tạo
     */
    private String createAt;
    /**
     * Người cập nhật
     */
    private String updatedBy;

    /**
     * Thời gian cập nhật
     */
    private String updateAt;

    /**
     * Cờ xóa
     */
    private Boolean isDeleted;

    /**
     * Tên món
     */
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
     * Trạng thái món {@link EStatusFood}
     */
    @Enumerated(EnumType.STRING)
    private EStatusFood status;

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
