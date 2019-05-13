package com.example.shop;

import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, ProductRepository.class})
public class ECommerceStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceStoreApplication.class, args);
    }

}
