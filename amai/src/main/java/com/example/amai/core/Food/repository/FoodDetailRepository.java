package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Integer> {
    /**
     * chi tiết món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodDetail> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm chi tiết món theo món và nguyên liệu
     */
    @Query(value = "select * from food_detail as fd " +
            "inner join food as f on fd.food_id = f.id " +
            "inner join material as m on fd.material_id = m.id " +
            "where fd.is_deleted =:isDeleteFoodDetail" +
            " and f.is_deleted =:isDeleteFood" +
            " and m.is_deleted =:isDeleteMaterial" +
            " and f.name =:nameFood " +
            "and m.name=:nameMaterial", nativeQuery = true)
    List<FoodDetail> findAllByFoodDetaillFoodNameAndMaterialNam(@Param("isDeleteFoodDetail") boolean isDeleteFoodDetail,
                                                                @Param("isDeleteFood") boolean isDeleteFood,
                                                                @Param("isDeleteMaterial") boolean isDeleteMaterial,
                                                                @Param("nameFood") String nameFood,
                                                                @Param("nameMaterial") String nameMaterial);
}
