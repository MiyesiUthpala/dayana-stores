package com.stores.dayana.repository;

import com.stores.dayana.dto.StockWorthDto;
import com.stores.dayana.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Item,Integer> {

    // Count expired items where expiration_date < today
    @Query("SELECT COUNT(i) FROM Item i WHERE i.expiration_date IS NOT NULL AND i.expiration_date < :today")
    int countExpiredItems(LocalDate today);

    // Count items where status is 'Low'
    @Query("SELECT COUNT(i) FROM Item i WHERE i.status = 'Low'")
    int countLowStockItems();

    // Calculate total stock net worth: SUM(current_qty * unit_price)
    @Query("SELECT SUM(i.current_qty * i.unit_price) FROM Item i")
    BigDecimal calculateTotalStockNetWorth();

    // get the low stock items
    @Query("SELECT i FROM Item i WHERE i.current_qty <= i.reorder_level")
    List<Item> findLowStockItems();

    // get expired items
    @Query("SELECT i FROM Item i WHERE i.expiration_date < CURRENT_DATE")
    List<Item> findExpiredItems();


    @Query(value = "SELECT i.category, SUM(i.current_qty * i.unit_price), COUNT(DISTINCT i.item_id), SUM(i.current_qty), AVG(i.unit_price) FROM inventory i GROUP BY i.category", nativeQuery = true)
    List<Object[]> getStockWorthByCategory();


}
