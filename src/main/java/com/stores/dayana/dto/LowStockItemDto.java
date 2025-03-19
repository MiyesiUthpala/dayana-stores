package com.stores.dayana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LowStockItemDto {

    private int item_id;
    private String item_name;
    private String item_image;
    private String category;
    private int current_qty;
    private int reorder_level;
    private int suggested_restock_amount;
}
