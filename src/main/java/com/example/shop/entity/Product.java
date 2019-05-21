package com.example.shop.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description", length = 500)
    @NotNull
    @NotEmpty
    private String description;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "products")
    private List<Orders> ordersLists;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "price")
    @NotNull
    private int price;

}
