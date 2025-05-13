package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
