package com.stores.dayana.controller;

import com.stores.dayana.entity.Order;
import com.stores.dayana.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public Order checkout() {
        return orderService.createOrder();
    }
}
