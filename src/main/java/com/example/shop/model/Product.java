package com.example.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;
    @Column(name = "price")
    private String price;
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte [] image;

    public Product() {
    }

    public Product(String name, String info, String price, byte[] image) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.image = image;
    }
}
