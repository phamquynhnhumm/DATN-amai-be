package com.example.amai.core.order.repository;

import com.example.amai.core.order.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Oder, Integer> {
    /**
     * Danh sách đơn hàng
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Oder> findByIsDeleted(boolean idDelete);


    /**
     * Tìm kiếm theo nguyên liệu theo tên , trạng thái isdelete, đơn vị tính, nhà cung cấp
     */
//    @Query(value = "select * from oder as m inner join supplier as sp on m.supplier_id = sp.id  where m.is_deleted = :isDelete and m.name like %:name% and m.unit like %:unit%  and sp.name like %:supplierName%", nativeQuery = true)
//    List<Oder> findAllByMaterialIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("name") String name, @Param("unit") String unit, @Param("supplierName") String supplierName);

}

