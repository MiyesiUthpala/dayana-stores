package com.stores.dayana.service;

import com.stores.dayana.entity.Supplier;
import com.stores.dayana.exception.ResourceNotFoundException;
import com.stores.dayana.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    // Constructor-based Dependency Injection (Best Practice)
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Retrieve all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Retrieve a supplier by ID
    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with ID: " + id));
    }

    // Add a new supplier
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Update an existing supplier
    public Supplier updateSupplier(Integer id, Supplier supplierDetails) {
        Supplier supplier = getSupplierById(id); // Reusing existing method for consistency

        // Update fields
        supplier.setSupplierName(supplierDetails.getSupplierName());
        supplier.setSupplierAddress(supplierDetails.getSupplierAddress());
        supplier.setSupplierPhone(supplierDetails.getSupplierPhone());
        supplier.setSupplierEmail(supplierDetails.getSupplierEmail());
        supplier.setProductCategory(supplierDetails.getProductCategory());
        supplier.setProductsSupplied(supplierDetails.getProductsSupplied());

        return supplierRepository.save(supplier);
    }

    // Delete a supplier
    public void deleteSupplier(Integer id) {
        Supplier supplier = getSupplierById(id); // Ensure existence before deletion
        supplierRepository.delete(supplier);
    }
}
