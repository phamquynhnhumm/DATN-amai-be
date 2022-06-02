package com.example.amai.core.shop.repository;

import com.example.amai.core.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository  extends JpaRepository<Shop, Integer>  {
}
