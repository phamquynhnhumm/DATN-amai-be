package com.example.amai.core.order.entity;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.order.entity.listener.OrderDetailListener;
import lombok.*;

import javax.persistence.*;

@Entity
@EntityListeners(OrderDetailListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
/**
 * Chi tiết đặt món
 */
public class OrderDetail {

    /**
     * ID chi tiết đặt món
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
     * món {@link Oder}
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Oder orders;

    /**
     * Số lượng
     */
    private int quantity;

    /**
     * món {@link Food}
     */
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
