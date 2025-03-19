package com.stores.dayana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {

    private int totalExpiredItems;
    private int totalLowStockItems;
    private double totalStockNetWorth;
}
