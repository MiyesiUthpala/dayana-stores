package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.CartItem;
import com.stores.dayana.entity.Order;
import com.stores.dayana.entity.OrderItem;
import com.stores.dayana.repository.CartItemRepository;
import com.stores.dayana.repository.OrderRepository;
import com.stores.dayana.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Order createOrder() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getItem().getUnit_price().doubleValue() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setTotalAmount(totalAmount);
        order.setStatus("CONFIRMED");

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(order, cartItem))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        orderRepository.save(order);
        cartItemRepository.deleteAll(); // Clear cart after placing the order

        return order;
    }
}
