package com.stores.dayana.repository;

import com.stores.dayana.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Item,Integer> {


}
