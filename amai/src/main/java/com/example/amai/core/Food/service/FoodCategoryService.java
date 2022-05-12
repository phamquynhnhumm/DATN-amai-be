package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.FoodCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodCategoryService extends IService<FoodCategory, Integer> {
    /**
     * Danh sách danh mục món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodCategory> findByIsDeleted(boolean idDelete);


}
