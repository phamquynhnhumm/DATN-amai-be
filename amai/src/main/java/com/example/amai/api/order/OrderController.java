package com.example.amai.api.order;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Danh sách order
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Oder>> finAll() {
        List<Oder> oderList = orderService.getAll();
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }

    /**
     * Xóa order (cập nhật cơ xóa isDelete = true
     *
     * @param id ID order
     * @return order đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Oder> deleteFood(@PathVariable("id") Integer id) {
        Oder oder = orderService.getById(id).orElse(null);
        if (oder.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            oder.setIsDeleted(true);
            orderService.save(oder);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một order
     *
     * @param id Id của order
     * @return trả về thông tin order nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Oder> findById(@PathVariable("id") Integer id) {
        Oder oder = orderService.getById(id).orElse(null);
        return oder.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oder, HttpStatus.OK);
    }

    /**
     * Thêm mới order
     *
     * @param oder
     * @return
     */
    @PostMapping
    public ResponseEntity<Oder> createFood(@RequestBody Oder oder) {
        if (oder.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(orderService.save(oder));
    }

    /**
     * Cập nhật order
     *
     * @param oder
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Oder> editFood(@RequestBody Oder oder) {
        Optional<Oder> oderOptional = orderService.getById(oder.getId());
        if (!oderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.save(oder), HttpStatus.OK);
    }
}
