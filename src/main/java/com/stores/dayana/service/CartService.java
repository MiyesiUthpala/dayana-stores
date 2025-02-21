package com.stores.dayana.service;

import com.stores.dayana.entity.CartItem;

import java.util.List;

public interface CartService {
    CartItem addToCart(Long productId, int quantity);
    void updateCartItem(Long id, int quantity);
    void removeCartItem(Long id);
    List<CartItem> getAllCartItems(); // Add this method to fix the error
}
