package com.example.amai.core.Food.repository;

import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.Food.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    /**
     * Danh sách nguyên liệu
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Material> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm nguyên liệu theo nhà cung cấp
     *
     * @param idSupplier id nhà cung cấp
     * @return list nguyên liệu có cùng id nhà cung cấp
     */
    List<Material> findAllBySupplierList_Id(Integer idSupplier);

    /**
     * Tìm kiếm theo nguyên liệu theo tên , trạng thái isdelete, đơn vị tính, nhà cung cấp
     */
    @Query(value = "select * from material as m inner join supplier as sp on m.supplier_id = sp.id  where m.is_deleted = :isDelete and m.name like %:name% and m.unit like %:unit%  and sp.name like %:supplierName%", nativeQuery = true)
    List<Material> findAllByMaterialIsDeletedAndName(@Param("isDelete") boolean isDelete, @Param("name") String name, @Param("unit") String unit, @Param("supplierName") String supplierName);
}

