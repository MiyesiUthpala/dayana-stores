package com.stores.dayana.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemId implements Serializable {

    private Long orderId;
    private Long cartItemId;

    public OrderItemId() {}

    public OrderItemId(Long orderId, Long cartItemId) {
        this.orderId = orderId;
        this.cartItemId = cartItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(cartItemId, that.cartItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, cartItemId);
    }
}
