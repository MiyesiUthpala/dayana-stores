package com.stores.dayana.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "orders_items")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemId id = new OrderItemId();

    @ManyToOne
    @MapsId("orderId") // Maps the orderId field in OrderItemId
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("cartItemId") // Maps the cartItemId field in OrderItemId
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public OrderItem() {}

    public OrderItem(Order order, CartItem cartItem) {
        this.order = order;
        this.cartItem = cartItem;
        this.id = new OrderItemId(order.getId(), cartItem.getId());
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
