package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodCategory;
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

    /**
     * Tìm kiếm món theo danh mục món
     *
     * @param idFoodCategory id danh mục món
     * @return list món có cùng id danh mục
     */
    List<Food> findAllByFoodCategory_Id(Integer idFoodCategory);

    /**
     * tìm kiếm theo tên món
     * @param isDelete
     * @param name
     * @return
     */
    List<Food> findAllByFoodIsDeletedAndName(boolean isDelete, String name);
}
