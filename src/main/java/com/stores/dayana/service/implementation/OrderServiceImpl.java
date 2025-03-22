package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.CartItem;
import com.stores.dayana.entity.Item;
import com.stores.dayana.entity.Order;
import com.stores.dayana.entity.OrderItem;
import com.stores.dayana.repository.CartItemRepository;
import com.stores.dayana.repository.InventoryRepository;
import com.stores.dayana.repository.OrderRepository;
import com.stores.dayana.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final InventoryRepository inventoryRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartItemRepository cartItemRepository,
                            InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public Order createOrder() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getItem().getUnit_price().doubleValue() * item.getQuantity())
                .sum();

        Order order = new Order("CONFIRMED", totalAmount);

        cartItems.forEach(cartItem -> {
            Item inventoryItem = cartItem.getItem();

            int remainingQty = inventoryItem.getCurrent_qty() - cartItem.getQuantity();

            if (remainingQty < 0) {
                throw new RuntimeException("Insufficient inventory for item: " + inventoryItem.getItem_name());
            }

            inventoryItem.setCurrent_qty(remainingQty);

            if (remainingQty == 0) {
                inventoryItem.setStatus("Out of Stock");
            }

            inventoryRepository.save(inventoryItem);

            OrderItem orderItem = new OrderItem(
                    (long) inventoryItem.getItem_id(),  // explicitly cast int to long
                    inventoryItem.getItem_name(),
                    cartItem.getQuantity(),
                    inventoryItem.getUnit_price().doubleValue()
            );


            order.addItem(orderItem);
        });

        orderRepository.save(order);

        cartItemRepository.deleteAll();

        return order;
    }
}