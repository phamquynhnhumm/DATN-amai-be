package com.example.amai.core.Food.service;

import com.example.amai.core.Food.entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService extends IService<Material, Integer> {

    /**
     * Danh sách món tùy theo trạng thái truyền vào
     *
     * @param idDelete
     * @return
     */
    List<Material> findByIsDeleted(boolean idDelete);

    /**
     * Danh  sách nguyên liệu có cùng mã nhà  cung cấp
     *
     * @param idSupplier
     * @return
     */
    List<Material> findAllBySupplierList_Id(Integer idSupplier);

    /**
     * Tìm kiếm nguyên liệu theo tên, trạng thái xóa, số lượng, tên nhà cung cấp
     *
     * @param isDelete
     * @param name
     * @param unit
     * @param supplierName
     * @return
     */
    List<Material> findAllByMaterialIsDeletedAndName(boolean isDelete, String name, String unit, String supplierName);
}
