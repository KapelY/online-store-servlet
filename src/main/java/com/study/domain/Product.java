package com.study.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime dateTime;

    public Product(String name, Double price, LocalDateTime dateTime) {
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
    }
}

