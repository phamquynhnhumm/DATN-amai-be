package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Oder, Integer> {
    /**
     * Danh sách đơn hàng
     *
     * @param idDeleteOder true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Oder> findByIsDeletedAndAccount_IsDeleted(boolean idDeleteOder, boolean issDeleteAccount);

    /**
     * Danh sách đơn hàng theo tài khoản
     *
     * @param idDeleteOder
     * @param issDeleteAccount
     * @return
     */
    List<Oder> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(boolean idDeleteOder, boolean issDeleteAccount, String userName);

    @Query(value = "select * from oder as od" +
            "inner join account as ac on ac.user_name = od.account" +
            "where od.is_deleted = :isDeleteOder " +
            "and od.is_deleted = :isDeleteAccount " +
            "and od.full_name like %:fullName% " +
            "and ac.user_name  like  %:userName% " +
            "and  od.address  like  %:address%" +
            "and od.phone  like  %:phone%", nativeQuery = true)
    List<Oder> findAllSerachOder(@Param("isDeleteOder") boolean isDeleteOder,
                                 @Param("isDeleteAccount") boolean isDeleteAccount,
                                 @Param("fullName") String fullName,
                                 @Param("userName") String userName,
                                 @Param("address") String address,
                                 @Param("phone") String phone);

    List<Oder> findAllByIsDeletedFalseAndStatus(EStatusOrder status);
}

