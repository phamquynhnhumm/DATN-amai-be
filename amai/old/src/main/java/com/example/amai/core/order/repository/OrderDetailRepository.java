package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    /**
     * Hiển thị danmh sách chi tiết món theo idOder
     *
     * @param idOders
     * @return
     */
//    @Query(value = "select * from order_detail where is_deleted = :idorderdetail and  order_id =:idOders;", nativeQuery = true)
    List<OrderDetail> findByIsDeletedAndOrders_Id(boolean idorderdetail, Integer idOders);

    List<OrderDetail> findAllByOrders_Id(Integer idOser);
}
