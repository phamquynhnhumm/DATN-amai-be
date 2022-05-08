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
}