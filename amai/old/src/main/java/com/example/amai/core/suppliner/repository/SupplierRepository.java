package com.example.amai.core.suppliner.repository;

import com.example.amai.core.suppliner.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    /**
     * Danh sách nhà cung cấp
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Supplier> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm theo nhà cung cấp theo tên, emai, dịa chỉ, số điện thoại
     */
    @Query(value = "select * from supplier as s where s.is_deleted = :isDelete and s.name like %:name% and s.email like %:email%  and s.phone like %:phone% and s.address like %:address%", nativeQuery = true)
    List<Supplier> findAllBySupplierNameandEmaiPhoneAddress(@Param("isDelete") boolean isDelete, @Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("address") String address);
}
