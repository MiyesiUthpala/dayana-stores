package com.stores.dayana.controller;

import com.stores.dayana.entity.Transaction;
import com.stores.dayana.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/transactions")
@CrossOrigin(origins = "http://localhost:3000")

public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction != null) {
            existingTransaction.setSupplierName(transaction.getSupplierName());
            existingTransaction.setProduct(transaction.getProduct());
            existingTransaction.setProductCategory(transaction.getProductCategory());
            existingTransaction.setPrice(transaction.getPrice());
            existingTransaction.setQuantity(transaction.getQuantity());
            existingTransaction.setDate(transaction.getDate());
            existingTransaction.setTotalAmount(transaction.getPrice() * transaction.getQuantity());
            return ResponseEntity.ok(transactionRepository.save(existingTransaction));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
