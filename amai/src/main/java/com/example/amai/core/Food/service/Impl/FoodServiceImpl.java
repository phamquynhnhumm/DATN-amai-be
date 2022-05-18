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
    public List<Food> findByIsDeleted(boolean idDeleteFood, boolean idDeleteFoodCategory) {
        return foodRepository.findByIsDeletedAndFoodCategory_IsDeleted(idDeleteFood, idDeleteFoodCategory);
    }

    @Override
    public List<Food> findByIsDeletedFood(boolean idDelete) {
        return foodRepository.findByIsDeleted(idDelete);
    }

    @Override
    public List<Food> findAllByFoodCategory_Id(Integer idFoodCategory) {
        return foodRepository.findAllByFoodCategory_Id(idFoodCategory);
    }

    @Override
    public List<Food> findAllByFoodIsDeletedAndName(boolean isDelete, String name, String unit, String foodCategoryName) {
        return foodRepository.findAllByFoodIsDeletedAndName(isDelete, name, unit, foodCategoryName);
    }

    @Override
    public List<Food> findAllByFoodByIsdeleteAndMaterial(boolean isDelete, Integer id) {
        return foodRepository.findAllByFoodByIsdeleteAndMaterial(isDelete, id);
    }
}
