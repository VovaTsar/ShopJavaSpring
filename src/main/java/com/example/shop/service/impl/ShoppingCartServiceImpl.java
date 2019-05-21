package com.example.shop.service.impl;

import com.example.shop.entity.Orders;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
//один экземпляр бина на каждую HTTP сессию
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private static final String USER_STATUS = "Wait for confirmation by the buyer";
    private static final String ADMIN_STATUS = "Waiting for confirmation from the seller";

    @Autowired
    public ShoppingCartServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void addProduct(Product product, User user) {
        Orders orders = orderRepository.findByUserIdAndStatus(user.getId(), USER_STATUS).orElse(null);
        if (orders == null) {
            orders = create(user);
        }
        orders.getProducts().add(product);
        orderRepository.save(orders);
    }

    @Override
    public void removeProduct(Product product, int userId) {
        Orders orders = orderRepository.findByUserIdAndStatus(userId, USER_STATUS).orElse(null);
        if (orders != null) {
            orders.getProducts().remove(product);
            orderRepository.save(orders);
        }
    }

    @Override
    public void clearProducts(int userId) {
        Orders orders = orderRepository.findByUserIdAndStatus(userId, USER_STATUS).orElse(null);
        if (orders != null) {
            orders.getProducts().clear();
            orderRepository.save(orders);
        }
    }

    @Override
    public int totalPrice(int userId) {
        int totalPrice = 0;
        Orders orders = orderRepository.findByUserIdAndStatus(userId, USER_STATUS).orElse(null);
        if (orders != null) {
            for (Product product : orders.getProducts()) {

                totalPrice += product.getPrice();
            }
            orders.setPriceOrd(totalPrice);
            orderRepository.save(orders);
        }
        return totalPrice;
    }

    @Override
    public Orders create(User user) {
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setPriceOrd(0);
        orders.setStatus(USER_STATUS);
        return orderRepository.save(orders);
    }

    @Override
    public Orders getOrders(User user) {
        Orders orders = orderRepository.findByUserIdAndStatus(user.getId(), USER_STATUS).orElse(null);
        if (orders == null) {
            orders = new Orders();
            orders.setPriceOrd(0);
            orders.setStatus(USER_STATUS);
        }
        return orders;
    }

    @Override
    public List<Orders> findOrdersByUserId(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public void cartCheckout(int userId) {
        Orders orders = orderRepository.findByUserIdAndStatus(userId, USER_STATUS).orElse(null);
        if (orders != null) {
            orders.setStatus(ADMIN_STATUS);
            orderRepository.save(orders);
        }
    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders findById(int id) {
        return orderRepository.findById(id);
    }


}
