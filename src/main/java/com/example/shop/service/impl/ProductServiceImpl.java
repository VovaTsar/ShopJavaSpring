package com.example.shop.service.impl;

import com.example.shop.entity.Product;

import com.example.shop.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return null;
    }
}