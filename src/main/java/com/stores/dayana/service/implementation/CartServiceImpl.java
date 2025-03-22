package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.CartItem;
import com.stores.dayana.entity.Item;
import com.stores.dayana.repository.CartItemRepository;
import com.stores.dayana.repository.InventoryRepository;
import com.stores.dayana.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public CartItem addToCart(Long itemId, int quantity) {
        // Find the item from inventory
        Item item = inventoryRepository.findById(itemId.intValue())
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        // Check item availability
        if (item.getStatus().equalsIgnoreCase("Out of Stock") || item.getCurrent_qty() < quantity) {
            throw new RuntimeException("Item is not available or insufficient quantity.");
        }

        // Create cart item and save
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void updateCartItem(Long itemId, int quantity) {
        CartItem cartItem = cartItemRepository.findByItemId(itemId.intValue())
                .orElseThrow(() -> new RuntimeException("Cart item not found with Item ID: " + itemId));

        Item item = cartItem.getItem();
        if (item.getCurrent_qty() < quantity) {
            throw new RuntimeException("Insufficient stock to update quantity.");
        }

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }


    @Override
    public void removeCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found with ID: " + id));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}
