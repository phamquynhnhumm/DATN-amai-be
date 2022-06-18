package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.contans.EStatusFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    /**
     * Danh sách món
     *
     * @param idDeleteFood true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Food> findByIsDeletedAndFoodCategory_IsDeleted(boolean idDeleteFood, boolean idDeleteFoodCategory);

    List<Food> findByIsDeletedAndFoodCategory_IsDeletedAndStatus(boolean idDeleteFood, boolean idDeleteFoodCategory, EStatusFood status);

    /**
     * List danh sách món có cùng danh  mục
     *
     * @param idDeleteFood
     * @param idDeleteFoodCategory
     * @param idFoodCategory
     * @return
     */
    List<Food> findByIsDeletedAndFoodCategory_IsDeletedAndFoodCategory_IdAndStatus(boolean idDeleteFood, boolean idDeleteFoodCategory, int idFoodCategory, EStatusFood status);

    /**
     * @param idDelete
     * @return
     */
    List<Food> findByIsDeletedAndStatus(boolean idDelete, EStatusFood status);

    /**
     * Tìm kiếm món theo danh mục món
     *
     * @param idFoodCategory id danh mục món
     * @return list món có cùng id danh mục
     */
    List<Food> findAllByIsDeletedFalseAndFoodCategory_Id(Integer idFoodCategory);

    /**
     * Tìm kiếm theo tên danh mục và trạng thái isdelete
     */
    @Query(value = "select * from food as f inner join food_category as fc on f.food_category_id = fc.id  where f.is_deleted = :isDelete and  fc.is_deleted = :isDeleteFoodCategory and f.name like %:name% and f.unit like %:unit%  and fc.name like %:foodCategoryName%", nativeQuery = true)
    List<Food> findAllByFoodIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("isDeleteFoodCategory") boolean isDeleteFoodCategory, @Param("name") String name, @Param("unit") String unit, @Param("foodCategoryName") String foodCategoryName);

    /**
     * Tìm kiếm food trên  trang user
     * Native SQL
     *
     * @param isDelete
     * @param isDeleteFoodCategory
     * @param name
     * @param foodCategoryName
     * @return
     */
    @Query(value = "select * from food as f inner join food_category as fc on f.food_category_id = fc.id  where f.is_deleted = :isDelete and  fc.is_deleted = :isDeleteFoodCategory and f.name like %:name% and fc.name like %:foodCategoryName%", nativeQuery = true)
    List<Food> findAllByFoodUserIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("isDeleteFoodCategory") boolean isDeleteFoodCategory, @Param("name") String name, @Param("foodCategoryName") String foodCategoryName);

    /**
     * Danh sách món có cùng sử dùng một nguyên liệu
     *
     * @param isDelete trạng thái tồnn tại của món
     * @param id       Id của nguyên liệu
     * @return
     */

    @Query(value = "select * from food_detail as fd inner join food as f on fd.food_id = f.id inner join material as m on fd.material_id = m.id where f.is_deleted =:isDelete and m.id =:id", nativeQuery = true)
    List<Food> findAllByFoodByIsdeleteAndMaterial(@Param("isDelete") boolean isDelete, @Param("id") Integer id);


    List<Food> findByOrderByNameAsc();
    List<Food> findByOrderByPriceAsc();
    List<Food> findByOrderByFoodCategory_NameAsc();

}
