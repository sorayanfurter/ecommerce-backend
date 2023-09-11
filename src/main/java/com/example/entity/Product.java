package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private String productDescription;
    private Double productDiscountedPrice;
    private Double productActualPrice;

}
