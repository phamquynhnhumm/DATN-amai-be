package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    /**
     * Danh sách món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Food> findByIsDeleted(boolean idDelete);
}
