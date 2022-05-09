package com.example.amai.api.order;

import com.example.amai.core.order.entity.OrderDetail;
import com.example.amai.core.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/orderdetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * Danh sách orderDetail
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OrderDetail>> finAll() {
        List<OrderDetail> orderDetailList = orderDetailService.getAll();
        return orderDetailList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }

    /**
     * Xóa orderDetail (cập nhật cơ xóa isDelete = true
     *
     * @param id ID orderDetail
     * @return orderDetail đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<OrderDetail> deleteFood(@PathVariable("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.getById(id).orElse(null);
        if (orderDetail.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            orderDetail.setIsDeleted(true);
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hàm Xem chi tiết một orderDetail
     *
     * @param id Id của orderDetail
     * @return trả về thông tin orderDetail nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.getById(id).orElse(null);
        return orderDetail.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    /**
     * Thêm mới orderDetail
     *
     * @param orderDetail
     * @return
     */
    @PostMapping
    public ResponseEntity<OrderDetail> createFood(@RequestBody OrderDetail orderDetail) {
        if (orderDetail.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    /**
     * Cập nhật orderDetail
     *
     * @param orderDetail
     * @return
     */
    @PutMapping("")
    public ResponseEntity<OrderDetail> editFood(@RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> oderOptional = orderDetailService.getById(orderDetail.getId());
        if (!oderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.OK);
    }
}


