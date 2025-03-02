package com.stores.dayana.mapper;

import com.stores.dayana.dto.InventoryDto;
import com.stores.dayana.entity.Item;

public class InventoryMapper {

    public static InventoryDto mapToInventoryDto(Item item){

        return new InventoryDto(
                    item.getItem_id(),
                    item.getItem_name(),
                    item.getItem_image(),
                    item.getCategory(),
                    item.getCurrent_qty(),
                    item.getMax_qty(),
                    item.getStatus(),
                    item.getUnit_price(),
                    item.getPurchase_date(),
                    item.getExpiration_date(),
                    item.getReorder_level(),
                    item.getLocation(),
                    item.getItem_added_on(),
                    item.getLast_updated()
                );
    }

    public static Item mapToItem(InventoryDto inventoryDto)
    {
        return new Item(
                inventoryDto.getItem_id(),
                inventoryDto.getItem_name(),
                inventoryDto.getItem_image(),
                inventoryDto.getCategory(),
                inventoryDto.getCurrent_qty(),
                inventoryDto.getMax_qty(),
                inventoryDto.getStatus(),
                inventoryDto.getUnit_price(),
                inventoryDto.getPurchase_date(),
                inventoryDto.getExpiration_date(),
                inventoryDto.getReorder_level(),
                inventoryDto.getLocation(),
                inventoryDto.getItem_added_on(),
                inventoryDto.getLast_updated()
        );
    }




}
