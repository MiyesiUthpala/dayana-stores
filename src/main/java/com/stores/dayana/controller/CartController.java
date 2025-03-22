package com.stores.dayana.controller;

import com.stores.dayana.entity.CartItem;
import com.stores.dayana.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/admin/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @PatchMapping("/update/{itemId}")
    public void updateCart(@PathVariable Long itemId, @RequestParam int quantity) {
        cartService.updateCartItem(itemId, quantity);
    }

    @DeleteMapping("/remove/{id}")
    public void removeCart(@PathVariable Long id) {
        cartService.removeCartItem(id);
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        return cartService.getAllCartItems();
    }
}
