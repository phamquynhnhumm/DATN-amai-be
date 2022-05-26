package com.example.amai.api.user;

import com.example.amai.core.order.entity.Cart;
import com.example.amai.core.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class cartController {
    @Autowired
    private CartService cartService;

    /**
     * list giỏ hàng theo tài khoản và trạng thái giỏ hàng
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<Cart>> findByIsDeletedAndCreatedBy_UserName(
            @RequestParam("userName") String userName
    ) {
        List<Cart> cartList = cartService.findByIsDeletedAndCreatedBy_UserName(false, false, userName);
        return cartList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    /**
     * Thêm mới giỏ hàng
     *
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        if (cart.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cartService.save(cart));
    }

    /**
     * Cập nhật giỏ hàng
     *
     * @param
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Cart> updteCart(@RequestBody Cart cart) {
        Optional<Cart> cartOptional = cartService.getById(cart.getId());
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }


    /**
     * Xóa giỏ hàng (cập nhật cơ xóa isDelete = true
     *
     * @param id ID món
     * @return Món đã được cập nhật cờ xóa
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable("id") Integer id) {
        Cart cart = cartService.getById(id).orElse(null);
        if (cart.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cart.setIsDeleted(true);
            cartService.save(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Hủy cart khỏi giỏ hàng khi đã thanh toán xong
     *
     * @param id
     * @return
     */
    @DeleteMapping("cancel/{id}")
    public ResponseEntity<Cart> deleteCartShopping(@PathVariable("id") Integer id) {
        Cart cart = cartService.getById(id).orElse(null);
        if (cart.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cartService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Chi tiết 1 giỏ hàng
     *
     * @return trả về thông tin món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable("id") Integer id) {
        Cart cart = cartService.getById(id).orElse(null);
        return cart.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("totalMoney")
    public ResponseEntity<Integer> totalMoney(
            @RequestParam("userName") String userName,
            @RequestParam("status") String status) {
        Integer cart = cartService.totalMoney(userName, status);
        return cart.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("totalQuantity")
    public ResponseEntity<Integer> totalQuantity(@RequestParam("userName") String userName, @RequestParam("status") String status) {
        Integer cart = cartService.totalQuantity(userName, status);
        return cart.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
