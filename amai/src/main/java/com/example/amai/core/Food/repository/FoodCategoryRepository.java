package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
    /**
     * Danh sách danh mục món
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<FoodCategory> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm theo tên danh mục và trạng thái isdelete
     */
    @Query(value = "select * from food_category as fc where fc.is_deleted = :isDelete and fc.name like %:name% ", nativeQuery = true)
    List<FoodCategory> findAllByIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("name") String name);
}
