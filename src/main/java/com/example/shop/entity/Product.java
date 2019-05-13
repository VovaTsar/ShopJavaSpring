package com.example.shop.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description",length = 500)
    @NotNull
    @NotEmpty
    private String description;

    @Column(name = "image")
    @URL
    private String image_url;

    @Column(name = "price")
    @NotNull
    private int price;

}
