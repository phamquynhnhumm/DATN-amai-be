package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByIsDeletedAndCreatedBy_IsDeletedAndCreatedBy_UserName(boolean idDeleteOder, boolean idAccount, String userName);


    @Query(value = "SELECT SUM(money) FROM cart WHERE is_deleted ='false' and created_by_user_name = :userName and status = :status", nativeQuery = true)
    Integer totalMoney(@Param("userName") String userName, @Param("status") String status);

    @Query(value = "SELECT SUM(quantity) FROM cart WHERE is_deleted ='false' and created_by_user_name = :userName and status = :status", nativeQuery = true)
    Integer totalQuantity(@Param("userName") String userName, @Param("status") String status);
}
