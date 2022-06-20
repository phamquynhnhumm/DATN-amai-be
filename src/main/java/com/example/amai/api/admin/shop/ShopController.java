package com.example.amai.api.admin.shop;

import com.example.amai.core.shop.entity.Shop;
import com.example.amai.core.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shop>> finAll() {
        List<Shop> shops = shopService.getAll();
        return shops.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(shops, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable("id") Integer id) {
        Shop shop = shopService.getById(id).orElse(null);
        return shop.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(shop, HttpStatus.OK);
    }

    /**
     * Thêm mới đơn hàng
     *
     * @param shop
     * @return
     */
    @PostMapping
    public ResponseEntity<Shop> createOrder(@RequestBody Shop shop) {
        if (shop.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(shopService.save(shop));
    }

    /**
     * Cập nhật đơn hàng
     *
     * @param shop
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Shop> updteOrder(@RequestBody Shop shop) {
        Optional<Shop> oderOptional = shopService.getById(shop.getId());
        if (!oderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shopService.save(shop), HttpStatus.OK);
    }
}
