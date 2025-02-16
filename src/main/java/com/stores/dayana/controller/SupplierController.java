package com.stores.dayana.controller;

import com.stores.dayana.entity.Supplier;
import com.stores.dayana.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://localhost:3000") // Adjust as needed
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Get all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    // Add a new supplier
    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.addSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    // Get supplier by ID (Fixed Integer ID)
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Integer id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
    }

    // Update supplier by ID
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Integer id, @Valid @RequestBody Supplier supplierDetails) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplierDetails);
        return ResponseEntity.ok(updatedSupplier);
    }

    // Delete supplier by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
