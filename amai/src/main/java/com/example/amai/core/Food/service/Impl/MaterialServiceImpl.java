package com.example.amai.core.Food.service.Impl;

import com.example.amai.core.Food.entity.Material;
import com.example.amai.core.Food.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialService materialService;

    @Override
    public List<Material> getAll() {
        return materialService.getAll();
    }

    @Override
    public Optional<Material> getById(Integer id) {
        return materialService.getById(id);
    }

    @Override
    public Material save(Material entity) {
        return materialService.save(entity);
    }

    @Override
    public List<Material> findByIsDeleted(boolean idDelete) {
        return materialService.findByIsDeleted(idDelete);
    }

    @Override
    public List<Material> findAllBySupplierList_Id(Integer idSupplier) {
        return materialService.findAllBySupplierList_Id(idSupplier);
    }

    @Override
    public List<Material> findAllByMaterialIsDeletedAndName(boolean isDelete, String name, String unit, String supplierName) {
        return materialService.findAllByMaterialIsDeletedAndName(isDelete, name, unit, supplierName);
    }
}
