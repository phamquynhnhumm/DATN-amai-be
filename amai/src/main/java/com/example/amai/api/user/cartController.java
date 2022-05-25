package com.example.amai.api.user;

import com.example.amai.core.Food.entity.Food;
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
    public ResponseEntity<List<Cart>> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(@RequestParam("userName") String userName
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
    public ResponseEntity<Cart> createOrder(@RequestBody Cart cart) {
        System.out.println(cart);
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
    public ResponseEntity<Cart> updteOrder(@RequestBody Cart cart) {
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
    public ResponseEntity<Cart> deleteFood(@PathVariable("id") Integer id) {
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
     * Chi tiết 1 giỏ hàng
     *
     * @return trả về thông tin món nếu tìm kiếm thấy Ngược lại trả về NOT
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable("id") Integer id) {
        Cart cart = cartService.getById(id).orElse(null);
        return cart.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
