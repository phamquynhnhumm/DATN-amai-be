package com.example.amai.core.suppliner.service.lmpl;

import com.example.amai.core.suppliner.entity.Supplier;
import com.example.amai.core.suppliner.repository.SupplierRepository;
import com.example.amai.core.suppliner.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> getById(Integer id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Supplier save(Supplier entity) {
        return supplierRepository.save(entity);
    }
}
