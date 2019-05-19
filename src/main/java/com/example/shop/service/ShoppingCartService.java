package com.example.shop.service;

import com.example.shop.entity.Orders;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface ShoppingCartService {
    void addProduct(Product product, User user);

    void removeProduct(Product product, int userId);

    void clearProducts(int userId);

    int totalPrice(int userId);

    Orders create(User user);

    Orders getOrders(User user);

    List<Orders> findOrdersByUserId(int userId);
}
