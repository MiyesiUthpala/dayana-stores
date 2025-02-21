package com.stores.dayana.service;

import com.stores.dayana.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
