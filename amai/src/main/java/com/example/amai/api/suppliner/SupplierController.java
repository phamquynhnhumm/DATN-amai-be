package com.example.amai.api.suppliner;

import com.example.amai.core.suppliner.entity.Supplier;
import com.example.amai.core.suppliner.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/admin/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * Danh sách Nhà cung cấp
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Supplier>> finAll() {
        List<Supplier> supplierList = supplierService.getAll();
        return supplierList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(supplierList, HttpStatus.OK);
    }

    /**
     * Danh sách nhà cung cấp  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Supplier>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Supplier> supplierList = supplierService.findByIsDeleted(isdelete);
        return supplierList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(supplierList, HttpStatus.OK);
    }

    /**
     * Hoàn tác việc xóa nhà cung cấp ( sửa từ trạng thái đã xóa thành không xóa)
     *
     * @param id
     * @return
     */

    @PutMapping("undelete/{id}")
    public ResponseEntity<Supplier> undeleteFood(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.getById(id).orElse(null);
        if (supplier.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            supplier.setIsDeleted(false);
            supplierService.save(supplier);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Xóa nhà cung cấp (cập nhật cơ xóa isDelete = true
     *
     * @param id ID nhà cung cấp
     * @return nhà cung cấp đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.getById(id).orElse(null);
        if (supplier.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            supplier.setIsDeleted(true);
            supplierService.save(supplier);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một nhà cung cấp
     *
     * @param id Id của nhà cung cấp
     * @return trả về thông tin nhà cung cấp nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.getById(id).orElse(null);
        return supplier.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    /**
     * Thêm mới nhà cung cấp
     *
     * @param supplier
     * @return
     */
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        if (supplier.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(supplierService.save(supplier));
    }

    /**
     * Cập nhật nhà cung cấp
     *
     * @param supplier
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Supplier> editSupplier(@RequestBody Supplier supplier) {
        Optional<Supplier> supplierOptional = supplierService.getById(supplier.getId());
        if (!supplierOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierService.save(supplier), HttpStatus.OK);
    }

    /**
     * Tìm kiếm danh mục theo tên và trạng thái xóa
     *
     * @param isDelete trạng thái xóa hoặc ko xoad ( 1 đã xóa ; 0 tồn tại)
     * @param name     Tên danh mục
     * @return Danh mục món tìm kiếm thấy
     */
    @GetMapping("/search")
    public ResponseEntity<List<Supplier>> searchSupplier(@RequestParam("isDelete") boolean isDelete,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("email") String email,
                                                    @RequestParam("phone") String phone,
                                                    @RequestParam("address") String address) {
        List<Supplier> supplierList = supplierService.findAllBySupplierNameandEmaiPhoneAddress(isDelete, name, email, phone, address);
        return supplierList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(supplierList, HttpStatus.OK);
    }
}
