package com.example.shop.service;

import com.example.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);


}
