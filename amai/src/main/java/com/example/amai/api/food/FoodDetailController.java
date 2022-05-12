package com.example.amai.api.food;

import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.entity.FoodDetail;
import com.example.amai.core.Food.service.FoodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/fooddetail")
public class FoodDetailController {
    @Autowired
    private FoodDetailService foodDetailService;

    /**
     * Danh sách chi tiết của món
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<FoodDetail>> finAll() {
        List<FoodDetail> foodDetailList = foodDetailService.getAll();
        return foodDetailList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodDetailList, HttpStatus.OK);
    }


    /**
     * Danh sách chi tiết món (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<FoodDetail>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<FoodDetail> foodCategoryList = foodDetailService.findByIsDeleted(isdelete);
        return foodCategoryList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategoryList, HttpStatus.OK);
    }

    /**
     * Xóa chi tiết món (cập nhật cơ xóa isDelete = true
     *
     * @param id ID chi tiết món
     * @return Chi tiết Món đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<FoodDetail> deleteFood(@PathVariable("id") Integer id) {
        FoodDetail foodDetail = foodDetailService.getById(id).orElse(null);
        if (foodDetail.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            foodDetail.setIsDeleted(true);
            foodDetailService.save(foodDetail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một chi tiết món
     *
     * @param id Id của  chi tiết món
     * @return trả về thông tin chi tiết món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<FoodDetail> findById(@PathVariable("id") Integer id) {
        FoodDetail foodDetail = foodDetailService.getById(id).orElse(null);
        return foodDetail.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodDetail, HttpStatus.OK);
    }

    /**
     * Thêm mới  chi tiết món
     *
     * @param foodDetail
     * @return
     */
    @PostMapping
    public ResponseEntity<FoodDetail> createFood(@RequestBody FoodDetail foodDetail) {
        if (foodDetail.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foodDetailService.save(foodDetail));
    }

    /**
     * Cập nhật  chi tiết món
     *
     * @param foodDetail
     * @return
     */
    @PutMapping("")
    public ResponseEntity<FoodDetail> editFood(@RequestBody FoodDetail foodDetail) {
        Optional<FoodDetail> foodDetailOptional = foodDetailService.getById(foodDetail.getId());
        if (!foodDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodDetailService.save(foodDetail), HttpStatus.OK);
    }
}
