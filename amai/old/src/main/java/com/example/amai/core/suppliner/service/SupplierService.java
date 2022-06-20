package com.example.amai.core.suppliner.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.suppliner.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService extends IService<Supplier, Integer> {
    /**
     * Danh sách nhà cung cấp
     *
     * @param idDelete true = 1 = đã xóa, false =0= chưa xóa
     * @return
     */
    List<Supplier> findByIsDeleted(boolean idDelete);

    /**
     * Tìm kiếm nhà cung cấp thêm tên, email, số điện thoại, địa chỉ
     * @param isDelete
     * @param name
     * @param email
     * @param phone
     * @param address
     * @return
     */
    List<Supplier> findAllBySupplierNameandEmaiPhoneAddress(boolean isDelete,String name,String email,String phone,String address);
}
