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
 * Đồ kèm theo món
 */
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
     * Tên Topping
     */
    private String name;

    /**
     * Gía
     */
    private int idFood;
}
