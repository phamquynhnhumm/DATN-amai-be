package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
    /**
     * Danh sách danh mục món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodCategory> findByIsDeleted(boolean idDelete);
}
