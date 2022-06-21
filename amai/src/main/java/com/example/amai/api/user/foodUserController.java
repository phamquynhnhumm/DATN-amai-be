package com.example.amai.api.user;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.Food.entity.contans.EStatusFood;
import com.example.amai.core.Food.service.FoodCategoryService;
import com.example.amai.core.Food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/food")
@CrossOrigin
public class foodUserController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodCategoryService foodCategoryService;

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
    @GetMapping("/allFood/{idFoodCategory}")
    public ResponseEntity<List<Food>> finAllIsDeleteandFoodCategory(@PathVariable("idFoodCategory") Integer idFoodCategory) {
        List<Food> foodList = foodService.findByIsDeletedAndFoodCategory_Id(false, false, idFoodCategory, EStatusFood.INSTOCK);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Danh sách món  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Food>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Food> foodList = foodService.findByIsDeletedFood(isdelete, EStatusFood.INSTOCK);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
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
     * Tìm kiếm món theo danh mục món
     *
     * @param idFoodCategory Id của danh mục
     * @return trả về thông tin món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/findByFoodcategoryId/{idFoodCategory}")
    public ResponseEntity<List<Food>> findAllByFoodCategory_Id(@PathVariable("idFoodCategory") Integer idFoodCategory) {
        List<Food> foodList = foodService.findAllByFoodCategory_Id(idFoodCategory);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Thêm mới món
     *
     * @param food
     * @return
     */
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        if (food.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foodService.save(food));
    }

    /**
     * Cập nhật món
     *
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

    /**
     * Tìm kiếm danh mục theo tên và trạng thái xóa
     *
     * @param isDelete trạng thái xóa hoặc ko xoad ( 1 đã xóa ; 0 tồn tại)
     * @param name  Tên danh mục
     * @return Danh mục món tìm kiếm thấy
     */
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searcFood(@RequestParam("isDelete") boolean isDelete,
                                                @RequestParam("name") String name,
                                                @RequestParam("foodCategoryName") String foodCategoryName
    ) {
        List<Food> foodList = foodService.findAllByFoodUserIsDeletedAndName(isDelete, false, name, foodCategoryName);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }



    /**
     * Danh sách món   (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/allFood/{idDeleteFood}/{idDeleteFoodCategory}")
    public ResponseEntity<List<Food>> finAllIsDeleteandFoodCategory(@PathVariable("idDeleteFood") boolean idDeleteFood,
                                                                    @PathVariable("idDeleteFoodCategory") boolean idDeleteFoodCategory) {
        List<Food> foodList = foodService.findByIsDeleteduser(idDeleteFood, idDeleteFoodCategory, EStatusFood.INSTOCK);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Danh sách món cú cùng một nguyên liệu
     *
     * @param isDelete trạng thái xóa của món
     * @param id       Id nguyên liệu
     * @return
     */
    @GetMapping("/searchfindBymaterial")
    public ResponseEntity<List<Food>> findAllByMaterialIsDeletedAndName(@RequestParam("isDelete") boolean isDelete,
                                                                        @RequestParam("id") Integer id
    ) {
        List<Food> foodList = foodService.findAllByFoodByIsdeleteAndMaterial(isDelete, id);
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * /**
     * Xắp xếp Food theo name
     */
    @GetMapping("oderByName")
    public ResponseEntity<List<Food>> OrderByNameACS() {
        List<Food> foodList = foodService.findByOrderByNameAsc();
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Xắp xếp Food theo tên danh mục món
     */
    @GetMapping("oderByFoodCategory")
    public ResponseEntity<List<Food>> OrderByFoodCategoryACS() {
        List<Food> foodList = foodService.findByOrderByFoodCategory_NameAsc();
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Xắp xếp Food theo giá
     */
    @GetMapping("oderByPrice")
    public ResponseEntity<List<Food>> OrderByPriceACS() {
        List<Food> foodList = foodService.findByOrderByPriceAsc();
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * All danh mục dùng pphía user
     */
    /**
     * Danh sách món  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/foodcategoryall")
    public ResponseEntity<List<FoodCategory>> finAlFoodCategory() {
        List<FoodCategory> foodList = foodCategoryService.findAllByFoodCategory_IsDeletedFalse();
        return foodList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    /**
     * Chi tiết 1 danh mục
     *
     * @param id
     * @return
     */
    @GetMapping("foodcategory/{id}")
    public ResponseEntity<FoodCategory> findByIdFoodCategory(@PathVariable("id") Integer id) {
        FoodCategory foodCategory = foodCategoryService.getById(id).orElse(null);
        return foodCategory.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(foodCategory, HttpStatus.OK);
    }
}