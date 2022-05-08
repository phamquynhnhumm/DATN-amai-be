package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
