package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @ManyToOne
    private Product product;

    @ManyToOne
    private UserEntity userEntity;

    public Cart(Product product, UserEntity userEntity) {
        this.product = product;
        this.userEntity = userEntity;
    }

    public Cart() {
    }
}
