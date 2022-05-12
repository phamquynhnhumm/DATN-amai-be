package com.example.amai.core.Food.service.Impl;

import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.repository.FoodCategoryRepository;
import com.example.amai.core.Food.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Override
    public List<FoodCategory> getAll() {
        return foodCategoryRepository.findAll();
    }

    @Override
    public Optional<FoodCategory> getById(Integer id) {
        return foodCategoryRepository.findById(id);
    }

    @Override
    public FoodCategory save(FoodCategory entity) {
        return foodCategoryRepository.save(entity);
    }

    @Override
    public List<FoodCategory> findByIsDeleted(boolean idDelete) {
        return foodCategoryRepository.findByIsDeleted(idDelete);
    }
}
