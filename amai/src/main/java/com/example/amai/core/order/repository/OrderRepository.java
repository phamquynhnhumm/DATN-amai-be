package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Oder, Integer> {
}
