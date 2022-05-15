package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.FoodDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodDetailService extends IService<FoodDetail, Integer> {
    /**
     * Danh sách chi tiết mín
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodDetail> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm chi tiết món theo tên món và tên nguyên liệu
     *
     * @param isDeleteFoodDetail
     * @param isDeleteFood
     * @param isDeleteMaterial
     * @param nameFood
     * @param nameMaterial
     * @return
     */
    List<FoodDetail> findAllByFoodDetaillFoodNameAndMaterialNam(boolean isDeleteFoodDetail, boolean isDeleteFood, boolean isDeleteMaterial, String nameFood, String nameMaterial);
}
