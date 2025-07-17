package com.yean.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private String description;

    private Double price;

    @Column(name = "created_at")
    private String createAt;

    @Column(name = "updated_at")
    private String updateAt;
}

