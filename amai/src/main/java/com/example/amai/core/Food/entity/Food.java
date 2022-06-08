package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.contans.EStatusFood;
import com.example.amai.core.Food.entity.listener.FoodListener;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.order.entity.Cart;
import com.example.amai.core.order.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(FoodListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "food")
    @JsonIgnore
   private List<Cart> cartList;

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
    private String image;

    /**
     * Nội dung món
     */
    private String content;
}
