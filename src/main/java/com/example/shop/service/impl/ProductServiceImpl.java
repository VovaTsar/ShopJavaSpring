package com.example.shop.service.impl;

import com.example.shop.entity.Product;

import com.example.shop.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public void save(Product product) {

    }

    @Override
    public void edit(long id, Product newProduct) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAllByOrderByIdAsc() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}