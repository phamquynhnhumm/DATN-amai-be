package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.listener.FoodDetailListener;
import com.example.amai.core.admin_user.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(FoodDetailListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Chi tiết món
 */
public class FoodDetail {
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
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * Tên nguyên liệu
     * material Id nguyên liệu {{@link Material}}
     */
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    /**
     * món {@link Food}
     */
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    /**
     * Khối lượng sử dụng
     */
    private Float kg;
}
