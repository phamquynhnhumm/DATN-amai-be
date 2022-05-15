package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Integer> {
    /**
     * chi tiết món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodDetail> findByIsDeleted(boolean idDelete);
}
