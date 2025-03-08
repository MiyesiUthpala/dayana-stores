package com.stores.dayana.repository;

import com.stores.dayana.entity.Transaction;  // Correct import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Optional but recommended
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
