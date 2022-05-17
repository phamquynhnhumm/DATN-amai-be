package com.example.amai.core.order.repository;

import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.order.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Oder, Integer> {
    /**
     * Danh sách đơn hàng
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Oder> findByIsDeleted(boolean idDelete);

    @Query(value = "select * from oder as od\n" +
            "inner join account as ac on ac.user_name = od.account \n" +
            "where od.is_deleted = :isDeleteOder " +
            "and od.is_deleted = :isDeleteAccount " +
            "and full_name like %:fullName% " +
            "and ac.user_name  like  %:userName% " +
            "and  address  like  %:address%" +
            "and phone  like  %:phone%;", nativeQuery = true)
    List<FoodDetail> findAllSerachOder(@Param("isDeleteOder") boolean isDeleteOder,
                                       @Param("isDeleteAccount") boolean isDeleteAccount,
                                       @Param("fullName") boolean fullName,
                                       @Param("userName") String userName,
                                       @Param("address") String address,
                                       @Param("phone") String phone);

}

