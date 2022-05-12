package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService extends IService<Food, Integer> {
    /**
     * Danh sách món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Food> findByIsDeleted(boolean idDelete);
}
