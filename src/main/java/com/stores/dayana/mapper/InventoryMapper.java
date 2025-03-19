package com.stores.dayana.mapper;

import com.stores.dayana.dto.ExpiredItemsDto;
import com.stores.dayana.dto.InventoryDto;
import com.stores.dayana.dto.LowStockItemDto;
import com.stores.dayana.dto.StockWorthDto;
import com.stores.dayana.entity.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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


    public static LowStockItemDto mapToLowStockItemDto(Item item){

        return new LowStockItemDto(
                item.getItem_id(),
                item.getItem_name(),
                item.getItem_image(),
                item.getCategory(),
                item.getCurrent_qty(),
                item.getReorder_level(),
                (item.getMax_qty() - item.getCurrent_qty())
        );
    }

    public static ExpiredItemsDto mapToExpiredItemDto(Item item) {
        long daysSinceExpired = ChronoUnit.DAYS.between(item.getExpiration_date(), LocalDate.now());
        // Convert int to BigDecimal before multiplication
        BigDecimal remainingStock = BigDecimal.valueOf(item.getCurrent_qty());
        BigDecimal potentialLoss = remainingStock.multiply(item.getUnit_price());
        return new ExpiredItemsDto(
                item.getItem_id(),
                item.getItem_name(),
                item.getItem_image(),
                item.getExpiration_date(),
                daysSinceExpired,
                potentialLoss.doubleValue()
        );
    }


}
