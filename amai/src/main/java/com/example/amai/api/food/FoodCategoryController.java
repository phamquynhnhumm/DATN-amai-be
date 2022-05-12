package com.example.amai.api.food;

import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/foodcategory")
public class FoodCategoryController {
    @Autowired
    private FoodCategoryService foodCategoryService;

    /**
     * Danh sách danh mục món tồn tại
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<FoodCategory>> finAll() {
        List<FoodCategory> foodCategoryList = foodCategoryService.findByIsDeletedFalse();
        return foodCategoryList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategoryList, HttpStatus.OK);
    }
    /**
     * Danh sách danh mục đã bị xóa
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<FoodCategory>> finAllDelete() {
        List<FoodCategory> foodCategoryList = foodCategoryService.findByIsDeletedTrue();
        return foodCategoryList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategoryList, HttpStatus.OK);
    }
    /**
     * Xóa danh mục món (cập nhật cơ xóa isDelete = true
     *
     * @param id ID danh mục món
     * @return Danh mục món đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<FoodCategory> deleteFood(@PathVariable("id") Integer id) {
        FoodCategory foodCategory = foodCategoryService.getById(id).orElse(null);
        if (foodCategory.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            foodCategory.setIsDeleted(true);
            foodCategoryService.save(foodCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hoàn tác việc xóa danh mục ( sửa từ trạng thái đã xóa thành không xóa)
     *
     * @param id
     * @return
     */

    @PutMapping("undelete/{id}")
    public ResponseEntity<FoodCategory> undeleteFood(@PathVariable("id") Integer id) {
        FoodCategory foodCategory = foodCategoryService.getById(id).orElse(null);
        if (foodCategory.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            foodCategory.setIsDeleted(false);
            foodCategoryService.save(foodCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một danh mục món
     *
     * @param id Id danh mục món
     * @return trả về thông tin danh mục món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<FoodCategory> findById(@PathVariable("id") Integer id) {
        FoodCategory foodCategory = foodCategoryService.getById(id).orElse(null);
        return foodCategory.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategory, HttpStatus.OK);
    }

    /**
     * Thêm mới danh mục món
     *
     * @param foodCategory
     * @return
     */
    @PostMapping
    public ResponseEntity<FoodCategory> createFood(@RequestBody FoodCategory foodCategory) {
        if (foodCategory.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foodCategoryService.save(foodCategory));
    }

    /**
     * Cập nhật danh mục món ăn
     *
     * @param foodCategory
     * @return
     */
    @PutMapping("")
    public ResponseEntity<FoodCategory> editFood(@RequestBody FoodCategory foodCategory) {
        Optional<FoodCategory> foodCategoryOptional = foodCategoryService.getById(foodCategory.getId());
        if (!foodCategoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodCategoryService.save(foodCategory), HttpStatus.OK);
    }
}

