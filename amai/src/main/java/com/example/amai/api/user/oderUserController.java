package com.example.amai.api.user;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.OrderDetail;
import com.example.amai.core.order.service.OrderDetailService;
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
public class oderUserController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    /**
     * Danh sách đơn hàng theo tài khoản (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/userName/{userName}")
    public ResponseEntity<List<Oder>> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(@PathVariable("userName") String userName) {
        List<Oder> oderList = orderService.findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(false, false, userName);
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }

    /**
     * Hàm Xem chi tiết đơn hàng
     *
     * @param id Id của nguyên liệu
     * @return trả về thông tin nguyên liệu nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Oder> findById(@PathVariable("id") Integer id) {
        Oder oder = orderService.getById(id).orElse(null);
        return oder.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oder, HttpStatus.OK);
    }

    /**
     * Danh sách chi tiết đơn hàng theo id Order
     *
     * @return trả về thông tin nguyên liệu nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/detail/{idOders}")
    public ResponseEntity<List<OrderDetail>> findByIdOder(@PathVariable("idOders") Integer idOders) {
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrders_IdAndIsDeletedIsFalse(false,idOders);
        return orderDetailList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }

    /**
     * Thêm mới đơn hàng
     *
     * @param oder
     * @return
     */
    @PostMapping
    public ResponseEntity<Oder> createOrder(@RequestBody Oder oder) {
        if (oder.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(orderService.save(oder));
    }

    /**
     * Cập nhật đơn hàng
     *
     * @param oder
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Oder> updteOrder(@RequestBody Oder oder) {
        Optional<Oder> oderOptional = orderService.getById(oder.getId());
        if (!oderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.save(oder), HttpStatus.OK);
    }

}

