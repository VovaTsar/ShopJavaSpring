package com.example.shop.repository;

import com.example.shop.entity.Orders;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
   List<Orders> findOrdersByUserId(int id);

   Optional<Orders> findByUserIdAndStatus(int id, String status);


}
