package com.stores.dayana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers") // Ensure this matches your database table name
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id") // Matches 'supplier_id' column in the database
    private Long supplierId; // ✅ Changed from Integer to Long

    @NotBlank(message = "Supplier name is required")
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @NotBlank(message = "Supplier address is required")
    @Column(name = "supplier_address", nullable = false)
    private String supplierAddress;

    @NotBlank(message = "Supplier phone is required")
    @Column(name = "supplier_phone", nullable = false)
    private String supplierPhone;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Supplier email is required")
    @Column(name = "supplier_email", nullable = false)
    private String supplierEmail;

    @NotBlank(message = "Product category is required")
    @Column(name = "product_category", nullable = false)
    private String productCategory;

    @NotBlank(message = "Products supplied are required")
    @Column(name = "products_supplied", nullable = false)
    private String productsSupplied;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Use LocalDateTime for better date/time handling

    // Default constructor (required by JPA)
    public Supplier() {
        this.createdAt = LocalDateTime.now(); // Automatically set creation time
    }

    // Constructor with parameters
    public Supplier(String supplierName, String supplierAddress, String supplierPhone,
                    String supplierEmail, String productCategory, String productsSupplied) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
        this.supplierEmail = supplierEmail;
        this.productCategory = productCategory;
        this.productsSupplied = productsSupplied;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getSupplierId() { // ✅ Changed from Integer to Long
        return supplierId;
    }

    public void setSupplierId(Long supplierId) { // ✅ Changed from Integer to Long
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductsSupplied() {
        return productsSupplied;
    }

    public void setProductsSupplied(String productsSupplied) {
        this.productsSupplied = productsSupplied;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
