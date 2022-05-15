package com.example.amai.core.Food.service.Impl;

import com.example.amai.core.Food.entity.Material;
import com.example.amai.core.Food.repository.MaterialRepository;
import com.example.amai.core.Food.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<Material> getById(Integer id) {

        return materialRepository.findById(id);
    }

    @Override
    public Material save(Material entity) {
        return materialRepository.save(entity);
    }

    @Override
    public List<Material> findByIsDeleted(boolean idDelete) {
        return materialRepository.findByIsDeleted(idDelete);
    }

    @Override
    public List<Material> findAllBySupplierList_Id(Integer idSupplier) {
        return materialRepository.findAllBySupplierList_Id(idSupplier);
    }

    @Override
    public List<Material> findAllByMaterialIsDeletedAndName(boolean isDelete, String name, String unit, String supplierName) {
        return materialRepository.findAllByMaterialIsDeletedAndName(isDelete, name, unit, supplierName);
    }
}
