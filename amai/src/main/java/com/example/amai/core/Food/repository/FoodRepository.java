package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.entity.FoodDetail;
import org.seasar.doma.jdbc.criteria.context.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
     *
     * @param idFoodCategory id danh mục món
     * @return list món có cùng id danh mục
     */
    List<Food> findAllByFoodCategory_Id(Integer idFoodCategory);


    /**
     * Tìm kiếm theo tên danh mục và trạng thái isdelete
     */
    @Query(value = "select * from food as f inner join food_category as fc on f.food_category_id = fc.id  where f.is_deleted = :isDelete and f.name like %:name% and f.unit like %:unit%  and fc.name like %:foodCategoryName%", nativeQuery = true)
    List<Food> findAllByFoodIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("name") String name, @Param("unit") String unit,@Param("foodCategoryName") String foodCategoryName);
}
