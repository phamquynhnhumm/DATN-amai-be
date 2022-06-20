package com.example.amai.core.Food.entity;

import com.example.amai.core.Food.entity.listener.FoodCategoryListener;
import com.example.amai.core.admin_user.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(FoodCategoryListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Danh mục món
 */
public class FoodCategory {
    /**
     * ID danh mục
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
     * Tên danh mục
     */
    private String name;

    /**
     * Danh sách món {@link Food}
     */
    @OneToMany(mappedBy = "foodCategory")
    @JsonIgnore
    private List<Food> foodList;
}
