package com.stores.dayana.service;

import com.stores.dayana.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    InventoryDto add_new_item(InventoryDto inventoryDto);
    List<InventoryDto> get_all_items();
    void delete_item(int itemId);
    String get_image_path_by_id(int id);

}
