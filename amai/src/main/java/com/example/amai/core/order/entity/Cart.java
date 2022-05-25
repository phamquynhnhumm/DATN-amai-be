package com.example.amai.core.order.entity;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.listener.FoodListener;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.order.entity.contans.EStatusCart;
import com.example.amai.core.order.entity.listener.CartListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(CartListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Giỏ hàng
 */
public class Cart {

    /**
     * ID đặt món
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
     * Trạng thái đặt món {@link EStatusCart}
     */
    @Enumerated(EnumType.STRING)
    private EStatusCart status;

    /**
     * Danh sách chi tiết đặt món{@link OrderDetail}
     */

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    /**
     * Tiền
     */
    private Float money;

    /**
     * Số lượng
     */
    private int quantity;
}