package com.example.amai.core.shop.service.Impl;

import com.example.amai.core.shop.entity.Shop;
import com.example.amai.core.shop.repository.ShopRepositorys;
import com.example.amai.core.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopRepositoryImpl implements ShopService {

    @Autowired
    private ShopRepositorys shopRepository;
    
    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> getById(Integer id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop save(Shop entity) {
        return shopRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.shopRepository.deleteById(id);
    }
}
