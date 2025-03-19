package com.stores.dayana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredItemsDto {

    private int item_id;
    private String item_name;
    private String item_image;
    private LocalDate expired_on;
    private long days_since_expired;
    private double potential_loss;
}
