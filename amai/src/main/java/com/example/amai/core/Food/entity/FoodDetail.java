package com.example.amai.core.Food.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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
     * Tên nguyên liệu
     * material Id nguyên liệu {{@link Material}}
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Material material;

    /**
     * món {@link Food}
     */
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    /**
     * Khối lượng sử dyngf
     */
    private Float kg;
}