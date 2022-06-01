package com.example.amai.api.admin.order;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.OrderDetail;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import com.example.amai.core.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Danh sách nguyên liệu
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Oder>> finAll() {
        List<Oder> oderList = orderService.getAll();
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }


    /**
     * Danh sách nguyên liệu khi  (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("/all/{isdelete}")
    public ResponseEntity<List<Oder>> finAllIsDelete(@PathVariable("isdelete") boolean isdelete) {
        List<Oder> oderList = orderService.findByIsDeleted(isdelete, false);
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Oder>> finAllStatus(@PathVariable("status") EStatusOrder status) {
        List<Oder> oderList = orderService.findAllByIsDeletedFalseAndStatus(status);
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }

    /**
     * Xóa đơn hàng (cập nhật cơ xóa isDelete = true
     *
     * @param id ID đơn hàng
     * @return đơn hàng đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Oder> deleteOrder(@PathVariable("id") Integer id) {
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
     * Hoàn tác việc xóa đơn hàng ( sửa từ trạng thái đã xóa thành không xóa)
     *
     * @param id
     * @return
     */

    @PutMapping("undelete/{id}")
    public ResponseEntity<Oder> undeleteOrder(@PathVariable("id") Integer id) {
        Oder oder = orderService.getById(id).orElse(null);
        if (oder.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            oder.setIsDeleted(false);
            orderService.save(oder);
            return new ResponseEntity<>(HttpStatus.OK);
        }
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

    @PutMapping("confirm")
    public ResponseEntity<Oder> confirmOder(@RequestBody Oder oder) {
        Optional<Oder> oderOptional = orderService.getById(oder.getId());
        oder.setStatus(EStatusOrder.CONFIRMED);
        if (!oderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.save(oder), HttpStatus.OK);
    }

    /**
     * Tìm kiếm theo nguyên liệu theo tên , trạng thái isdelete, đơn vị tính, nhà cung cấp
     *
     * @return nguyên liệu tìm kiếm thấy
     */
    @GetMapping("/search")
    public ResponseEntity<List<Oder>> searchOder(@RequestParam("isDeleteOder") boolean isDeleteOder,
                                                 @RequestParam("isDeleteAccount") boolean isDeleteAccount,
                                                    @RequestParam("fullName") String fullName,
                                                    @RequestParam("userName") String userName,
                                                    @RequestParam("address") String address,
                                                    @RequestParam("phone") String phone) {
        System.out.println(isDeleteAccount + address + fullName);
        List<Oder> oderList = orderService.findAllSerachOder(isDeleteOder, isDeleteAccount, fullName, userName, address, phone);
        if (oderList != null) {
            System.out.println("Có timg hay nha");
        }
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }
}
