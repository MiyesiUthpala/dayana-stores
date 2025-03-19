package com.stores.dayana.service;

import com.stores.dayana.dto.*;

import java.util.List;

public interface InventoryService {

    InventoryDto add_new_item(InventoryDto inventoryDto);
    List<InventoryDto> get_all_items();
    void delete_item(int itemId);
    String get_image_path_by_id(int id);
    InventoryDto update_item(InventoryDto updatedItem);
    StatisticsDto get_statistics();
    List<ExpiredItemsDto> get_expired_items();
    List<LowStockItemDto> get_low_stock_items();
    List<StockWorthDto> get_stock_worth();

}
