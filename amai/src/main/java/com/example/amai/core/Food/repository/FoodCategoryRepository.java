package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository  extends JpaRepository<FoodCategory, Integer> {
}
