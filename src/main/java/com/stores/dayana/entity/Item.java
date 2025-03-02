package com.stores.dayana.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
@Entity

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int item_id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "item_image")
    private String item_image;

    @Column(name = "category")
    private String category;

    @Column(name = "current_qty")
    private int current_qty;

    @Column(name = "max_qty")
    private int max_qty;

    @Column(name = "status")
    private String status;

    @Column(name = "unit_price")
    private BigDecimal unit_price;

    @Column(name = "purchase_date")
    private LocalDate purchase_date;

    @Column(name = "expiration_date",nullable = true)
    private LocalDate expiration_date;

    @Column(name = "reorder_level")
    private int reorder_level;

    @Column(name = "location")
    private String location;

    @Column(name = "item_added_on")
    private LocalDateTime item_added_on;

    @Column(name = "last_updated")
    private LocalDateTime last_updated;

    @PrePersist
    protected void onCreate() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
        String text = dateTime.format(formatter);
        this.item_added_on = LocalDateTime.parse(text, formatter);
        this.last_updated = LocalDateTime.parse(text, formatter); // Auto-set on creation
    }

    @PreUpdate
    public void setLastUpdated() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
        String text = date.format(formatter);
        this.last_updated = LocalDateTime.parse(text, formatter);
    }
}
