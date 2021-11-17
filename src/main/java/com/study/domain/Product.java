package com.study.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Product {
    private Long id;
    private String name;
    private Double price;
    private Timestamp creationDate;
}

