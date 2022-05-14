package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodDetail;
import org.seasar.doma.jdbc.criteria.context.Criterion;
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

    /**
     * Tìm kiếm món theo danh mục món
     * @param idFoodCategory id danh mục món
     * @return list món có cùng id danh mục
     */
    List<Food> findAllByFoodCategory_Id(Integer idFoodCategory);
}
