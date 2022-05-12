package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.FoodCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodCategoryService extends IService<FoodCategory, Integer> {
    /**
     * List ra những danh mục món đã bị xóa
     *
     * @return Danh mục món đã bị xóa
     */
    List<FoodCategory> findByIsDeletedTrue();

    /**
     * Hiển thị danh sách danh mục món còn tồn tại
     *
     * @return Danh mục món chưa bị xóa
     */
    List<FoodCategory> findByIsDeletedFalse();
}
