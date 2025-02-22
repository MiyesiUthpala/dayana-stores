package com.stores.dayana.controller;

import com.stores.dayana.entity.CartItem;
import com.stores.dayana.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @PatchMapping("/update/{id}")
    public void updateCart(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateCartItem(id, quantity);
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
