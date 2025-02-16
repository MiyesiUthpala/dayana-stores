package com.stores.dayana.repository;

import com.stores.dayana.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // Find a supplier by email
    Supplier findBySupplierEmail(String email);

    // Find suppliers by product category
    List<Supplier> findByProductCategory(String category);

    // Find suppliers whose names contain a certain string (case insensitive)
    List<Supplier> findBySupplierNameContainingIgnoreCase(String name);
}
