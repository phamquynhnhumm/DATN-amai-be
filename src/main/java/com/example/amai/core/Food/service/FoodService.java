package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.contans.EStatusFood;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService extends IService<Food, Integer> {
    /**
     * Danh sách món
     *
     * @param idDeleteFood true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Food> findByIsDeleted(boolean idDeleteFood, boolean idDeleteFoodCategory);

    List<Food> findByIsDeleteduser(boolean idDeleteFood, boolean idDeleteFoodCategory, EStatusFood status);

    List<Food> findByIsDeletedFood(boolean idDelete, EStatusFood status);

    List<Food> findByIsDeletedAndFoodCategory_Id(boolean idDeleteFood, boolean idDeleteFoodCategory, int idFoodCategory, EStatusFood status);

    /**
     * Tìm kiếm món theo danh mục món
     *
     * @param idFoodCategory id danh mục món
     * @return list món có cùng id danh mục
     */
    List<Food> findAllByFoodCategory_Id(Integer idFoodCategory);

    /**
     * tìm kiếm theo tên món
     *
     * @param isDelete
     * @param name
     * @return
     */
    List<Food> findAllByFoodIsDeletedAndName(boolean isDelete, boolean isDeleteFoodCategory, String name, String unit, String foodCategoryName);

    List<Food> findAllByFoodUserIsDeletedAndName(boolean isDelete, boolean isDeleteFoodCategory, String name, String foodCategoryName);

    /**
     * Danh sách cùng sử dùng chung một nguyên liệu
     *
     * @param isDelete của món
     * @param id của nguyên liệu
     * @return
     */
    List<Food> findAllByFoodByIsdeleteAndMaterial(boolean isDelete, Integer id);

    List<Food> findByOrderByNameAsc();
    List<Food> findByOrderByPriceAsc();
    List<Food> findByOrderByFoodCategory_NameAsc();
}
