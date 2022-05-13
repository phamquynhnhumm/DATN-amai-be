package com.example.amai.api.food;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    /**
     * Danh sách món
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Food>> finAll() {
        List<Food> foodList = foodService.getAll();
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }


    /**
     * Danh sách món  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Food>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Food> foodCategoryList = foodService.findByIsDeleted(isdelete);
        return foodCategoryList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategoryList, HttpStatus.OK);
    }
    /**
     * Xóa món (cập nhật cơ xóa isDelete = true
     *
     * @param id ID món
     * @return Món đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable("id") Integer id) {
        Food food = foodService.getById(id).orElse(null);
        if (food.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            food.setIsDeleted(true);
            foodService.save(food);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hoàn tác việc xóa món ( sửa từ trạng thái đã xóa thành không xóa)
     *
     * @param id
     * @return
     */

    @PutMapping("undelete/{id}")
    public ResponseEntity<Food> undeleteFood(@PathVariable("id") Integer id) {
        Food food = foodService.getById(id).orElse(null);
        if (food.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            food.setIsDeleted(false);
            foodService.save(food);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một món
     *
     * @param id Id của món
     * @return trả về thông tin món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable("id") Integer id) {
        Food food = foodService.getById(id).orElse(null);
        return food.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(food, HttpStatus.OK);
    }

    /**
     * Thêm mới món
     *
     * @param food
     * @return
     */
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        System.out.println(food);
        if (food.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foodService.save(food));
    }

    /**
     * Cập nhật món
     * @param food
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Food> editFood(@RequestBody Food food) {
        Optional<Food> foodOptional = foodService.getById(food.getId());
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }
}
