package com.stores.dayana.repository;

import com.stores.dayana.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c FROM CartItem c WHERE c.item.item_id = :itemId")
    Optional<CartItem> findByItemId(int itemId);
}
