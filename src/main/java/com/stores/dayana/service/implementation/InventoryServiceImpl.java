package com.stores.dayana.service.implementation;

import com.stores.dayana.dto.InventoryDto;
import com.stores.dayana.entity.Item;
import com.stores.dayana.mapper.InventoryMapper;
import com.stores.dayana.repository.InventoryRepository;
import com.stores.dayana.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    private String calculateStockStatus(int currentQty, int maxQty, int reorderLevel) {
        if (currentQty > Math.round((float) maxQty / 2) && currentQty <= maxQty) return "Available";
        if (currentQty > reorderLevel && currentQty <= Math.round((float) maxQty / 2)) return "Moderate";
        if (currentQty>0 && currentQty <= reorderLevel) return "Low";
        if (currentQty == 0) return "Out of Stock";
        return "Unknown"; // Fallback case
    }

    @Override
    public InventoryDto add_new_item(InventoryDto inventoryDto) {

        Item item= InventoryMapper.mapToItem(inventoryDto);
        item.setStatus(calculateStockStatus(item.getCurrent_qty(), item.getMax_qty(), item.getReorder_level()));
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

}
