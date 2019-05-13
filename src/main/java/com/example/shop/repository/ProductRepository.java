package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);
    Product findByName(String name);
    List<Product> findAllByOrderByIdAsc();
}
