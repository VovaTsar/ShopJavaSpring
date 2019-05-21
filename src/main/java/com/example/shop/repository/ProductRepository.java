package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);
    Product findByName(String name);
    @Query("SELECT p FROM Product AS p where p.deleted=false")
    List<Product> findAllByOrderByIdAsc();

}
