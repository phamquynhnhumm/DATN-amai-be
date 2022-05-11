package com.example.amai.core.order.entity;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.listener.Listener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(Listener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
