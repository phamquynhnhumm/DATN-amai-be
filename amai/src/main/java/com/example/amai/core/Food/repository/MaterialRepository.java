package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.Food.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    /**
     * Danh sách nguyên liệu
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Material> findByIsDeleted(boolean idDelete);
}

