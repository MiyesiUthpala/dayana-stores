package com.stores.dayana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockWorthDto {

    private String category;
    private BigDecimal totalStockValue;
    private long totalItems;
    private long totalQuantity;
    private BigDecimal averagePrice;
}
