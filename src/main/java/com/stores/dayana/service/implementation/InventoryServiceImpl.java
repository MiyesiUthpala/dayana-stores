package com.stores.dayana.service.implementation;

import com.stores.dayana.dto.*;
import com.stores.dayana.entity.Item;
import com.stores.dayana.mapper.InventoryMapper;
import com.stores.dayana.repository.InventoryRepository;
import com.stores.dayana.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;


    @Override
    public InventoryDto add_new_item(InventoryDto inventoryDto) {

        Item item= InventoryMapper.mapToItem(inventoryDto);
//        item.setStatus(calculateStockStatus(item.getCurrent_qty(), item.getMax_qty(), item.getReorder_level()));
        Item saved_item=inventoryRepository.save(item);
        return InventoryMapper.mapToInventoryDto(saved_item);
    }

    @Override
    public List<InventoryDto> get_all_items() {

        List<Item> items=inventoryRepository.findAll();
        return items.stream().map((item)->InventoryMapper.mapToInventoryDto(item)).collect(Collectors.toList());
    }

    @Override
    public void delete_item(int itemId) {
        inventoryRepository.deleteById(itemId);
    }

    @Override
    public String get_image_path_by_id(int id) {
        Optional<Item> item=inventoryRepository.findById(id);
        return item.map(Item::getItem_image).orElse(null);
    }

    @Override
    public InventoryDto update_item(InventoryDto updatedItem) {

        Optional<Item> optionalItem = inventoryRepository.findById(updatedItem.getItem_id());
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get(); // Get the existing item
            //updating values
            item.setItem_name(updatedItem.getItem_name());
            item.setItem_image(updatedItem.getItem_image());
            item.setCategory(updatedItem.getCategory());
            item.setCurrent_qty(updatedItem.getCurrent_qty());  //current qty
            item.setMax_qty(updatedItem.getMax_qty());                                                            // max qty
            item.setUnit_price(updatedItem.getUnit_price());
            item.setPurchase_date(updatedItem.getPurchase_date());
            item.setExpiration_date(updatedItem.getExpiration_date());
            item.setReorder_level(updatedItem.getReorder_level());
            item.setLocation(updatedItem.getLocation());

            Item editedItem=inventoryRepository.save(item);
            return InventoryMapper.mapToInventoryDto(editedItem);

        }else {
            return null;
        }
    }

    @Override
    public StatisticsDto get_statistics() {

        int totalExpiredItems = inventoryRepository.countExpiredItems(LocalDate.now());
        int totalLowStockItems = inventoryRepository.countLowStockItems();
        BigDecimal totalStockNetWorth = inventoryRepository.calculateTotalStockNetWorth();

        return new StatisticsDto(
                totalExpiredItems,
                totalLowStockItems,
                totalStockNetWorth != null ? totalStockNetWorth.doubleValue() : 0.0
        );
    }

    @Override
    public List<ExpiredItemsDto> get_expired_items() {
        List<Item> expiredItems = inventoryRepository.findExpiredItems();
        return expiredItems.stream()
                .map(InventoryMapper::mapToExpiredItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LowStockItemDto> get_low_stock_items() {
        List<Item> lowStockItems = inventoryRepository.findLowStockItems();
        return lowStockItems.stream()
                .map(InventoryMapper::mapToLowStockItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockWorthDto> get_stock_worth() {
        List<Object[]> results = inventoryRepository.getStockWorthByCategory();
        List<StockWorthDto> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            StockWorthDto dto = new StockWorthDto(
                    (String) row[0],                          // category
                    BigDecimal.valueOf(((Number) row[1]).doubleValue()),  // totalStockValue
                    ((Number) row[2]).longValue(),            // totalItems
                    ((Number) row[3]).longValue(),            // totalQuantity
                    BigDecimal.valueOf(((Number) row[4]).doubleValue())   // averagePrice
            );
            dtoList.add(dto);
        }
        return dtoList;    }

}

//