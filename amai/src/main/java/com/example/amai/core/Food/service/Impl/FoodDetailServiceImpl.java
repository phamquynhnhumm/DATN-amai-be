package com.example.amai.core.Food.service.Impl;

import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.Food.repository.FoodDetailRepository;
import com.example.amai.core.Food.service.FoodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodDetailServiceImpl implements FoodDetailService {
    @Autowired
    private FoodDetailRepository foodDetailRepository;

    @Override
    public List<FoodDetail> getAll() {
        return foodDetailRepository.findAll();
    }

    @Override
    public Optional<FoodDetail> getById(Integer id) {
        return foodDetailRepository.findById(id);
    }

    @Override
    public FoodDetail save(FoodDetail entity) {
        return foodDetailRepository.save(entity);
    }


    @Override
    public List<FoodDetail> findByIsDeleted(boolean idDelete) {
        return foodDetailRepository.findByIsDeleted(idDelete);
    }

    @Override
    public List<FoodDetail> findByIsDeletedAndFood_IsDeletedAndMaterial_IsDeleted(boolean idDeleteFoodDetail, boolean idDeleteFood, boolean isDeleteMaterail) {
        return foodDetailRepository.findByIsDeletedAndFood_IsDeletedAndMaterial_IsDeleted(idDeleteFoodDetail, idDeleteFood, isDeleteMaterail);
    }

    @Override
    public List<FoodDetail> findAllByFoodDetaillFoodNameAndMaterialNam(boolean isDeleteFoodDetail, boolean isDeleteFood, boolean isDeleteMaterial, String nameFood, String nameMaterial) {
        return foodDetailRepository.findAllByFoodDetaillFoodNameAndMaterialNam(isDeleteFoodDetail, isDeleteFood, isDeleteMaterial, nameFood, nameMaterial);
    }
}
