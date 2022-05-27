package com.example.amai.core.Food.service.Impl;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.repository.FoodRepository;
import com.example.amai.core.Food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAll() {
        return this.foodRepository.findAll();
    }

    @Override
    public Optional<Food> getById(Integer id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food save(Food entity) {
        return foodRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> findByIsDeleted(boolean idDeleteFood, boolean idDeleteFoodCategory) {
        return foodRepository.findByIsDeletedAndFoodCategory_IsDeleted(idDeleteFood, idDeleteFoodCategory);
    }

    @Override
    public List<Food> findByIsDeletedFood(boolean idDelete) {
        return foodRepository.findByIsDeleted(idDelete);
    }

    @Override
    public List<Food> findByIsDeletedAndFoodCategory_Id(boolean idDeleteFood, boolean idDeleteFoodCategory, int idFoodCategory) {
        return foodRepository.findByIsDeletedAndFoodCategory_IsDeletedAndFoodCategory_Id(idDeleteFood,idDeleteFoodCategory,idFoodCategory);
    }

    @Override
    public List<Food> findAllByFoodCategory_Id(Integer idFoodCategory) {
        return foodRepository.findAllByIsDeletedFalseAndFoodCategory_Id(idFoodCategory);
    }

    @Override
    public List<Food> findAllByFoodIsDeletedAndName(boolean isDelete,boolean isDeleteFoodCategory, String name, String unit, String foodCategoryName) {
        return foodRepository.findAllByFoodIsDeletedAndName(isDelete,isDeleteFoodCategory, name, unit, foodCategoryName);
    }

    @Override
    public List<Food> findAllByFoodUserIsDeletedAndName(boolean isDelete, boolean isDeleteFoodCategory, String name, String foodCategoryName) {
        return foodRepository.findAllByFoodUserIsDeletedAndName(isDelete, isDeleteFoodCategory, name, foodCategoryName);
    }

    @Override
    public List<Food> findAllByFoodByIsdeleteAndMaterial(boolean isDelete, Integer id) {
        return foodRepository.findAllByFoodByIsdeleteAndMaterial(isDelete, id);
    }

    @Override
    public List<Food> findByOrderByNameAsc() {
        return foodRepository.findByOrderByNameAsc();
    }

    @Override
    public List<Food> findByOrderByPriceAsc() {
        return foodRepository.findByOrderByPriceAsc();
    }

    @Override
    public List<Food> findByOrderByFoodCategory_NameAsc() {
        return foodRepository.findByOrderByFoodCategory_NameAsc();
    }
}
