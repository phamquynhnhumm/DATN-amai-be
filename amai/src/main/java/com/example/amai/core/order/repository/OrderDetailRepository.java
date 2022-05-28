package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    /**
     * Hiển thị danmh sách chi tiết món theo idOder
     *
     * @param idOders
     * @return
     */
    List<OrderDetail> findByIsDeletedAndOrders_Id(boolean idorderdetail,Integer idOders);
}
