package com.example.amai.core.Food.entity;

import com.example.amai.core.listener.Listener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(Listener.class)
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
