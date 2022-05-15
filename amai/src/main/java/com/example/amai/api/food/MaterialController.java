package com.example.amai.api.food;

import com.example.amai.core.Food.entity.Material;
import com.example.amai.core.Food.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    /**
     * Danh sách nguyên liệu
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Material>> finAll() {
        List<Material> materialList = materialService.getAll();
        return materialList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(materialList, HttpStatus.OK);
    }


    /**
     * Danh sách nguyên liệu  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Material>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Material> materialList = materialService.findByIsDeleted(isdelete);
        return materialList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    /**
     * Xóa nguyên liệu (cập nhật cơ xóa isDelete = true
     *
     * @param id ID nguyên liệu
     * @return Nguyên liệu đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Material> deleteFood(@PathVariable("id") Integer id) {
        Material material = materialService.getById(id).orElse(null);
        if (material.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            material.setIsDeleted(true);
            materialService.save(material);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hoàn tác việc xóa nguyên liệu ( sửa từ trạng thái đã xóa thành không xóa)
     *
     * @param id
     * @return
     */

    @PutMapping("undelete/{id}")
    public ResponseEntity<Material> undeleteFood(@PathVariable("id") Integer id) {
        Material material = materialService.getById(id).orElse(null);
        if (material.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            material.setIsDeleted(false);
            materialService.save(material);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một nguyên liệu
     *
     * @param id Id của nguyên liệu
     * @return trả về thông tin nguyên liệu nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Material> findById(@PathVariable("id") Integer id) {
        Material material = materialService.getById(id).orElse(null);
        return material.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(material, HttpStatus.OK);
    }

    /**
     * Tìm kiếm nguyên liệu theo nhà cung cấp
     *
     * @param idSupplier Id nhà cung cấp
     * @return trả về thông tin món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/findBySupplierId/{idSupplier}")
    public ResponseEntity<List<Material>> findAllByFoodCategory_Id(@PathVariable("idSupplier") Integer idSupplier) {
        List<Material> materialList = materialService.findAllBySupplierList_Id(idSupplier);
        return materialList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    /**
     * Thêm mới nguyên liệu
     *
     * @param material
     * @return
     */
    @PostMapping
    public ResponseEntity<Material> createFood(@RequestBody Material material) {
        if (material.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(materialService.save(material));
    }

    /**
     * Cập nhật nguyên liệu
     *
     * @param material
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Material> editFood(@RequestBody Material material) {
        Optional<Material> materialOptional = materialService.getById(material.getId());
        if (!materialOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(materialService.save(material), HttpStatus.OK);
    }

    /**
     * Tìm kiếm theo nguyên liệu theo tên , trạng thái isdelete, đơn vị tính, nhà cung cấp
     *
     * @param isDelete trạng thái xóa hoặc ko xoad ( 1 đã xóa ; 0 tồn tại)
     * @param name     Tên nguyên liệu
     * @return nguyên liệu tìm kiếm thấy
     */
    @GetMapping("/search")
    public ResponseEntity<List<Material>> searcFood(@RequestParam("isDelete") boolean isDelete,
                                                @RequestParam("name") String name,
                                                @RequestParam("unit") String unit,
                                                @RequestParam("supplierName") String supplierName
    ) {
        List<Material> materialList = materialService.findAllByMaterialIsDeletedAndName(isDelete, name, unit, supplierName);
        return materialList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(materialList, HttpStatus.OK);
    }
}
